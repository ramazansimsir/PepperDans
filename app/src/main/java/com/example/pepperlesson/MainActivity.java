package com.example.pepperlesson;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.builder.ChatBuilder;
import com.aldebaran.qi.sdk.builder.QiChatbotBuilder;
import com.aldebaran.qi.sdk.builder.TopicBuilder;
import com.aldebaran.qi.sdk.design.activity.RobotActivity;
import com.aldebaran.qi.sdk.object.conversation.AutonomousReactionImportance;
import com.aldebaran.qi.sdk.object.conversation.AutonomousReactionValidity;
import com.aldebaran.qi.sdk.object.conversation.Bookmark;
import com.aldebaran.qi.sdk.object.conversation.BookmarkStatus;
import com.aldebaran.qi.sdk.object.conversation.Chat;
import com.aldebaran.qi.sdk.object.conversation.QiChatbot;
import com.aldebaran.qi.sdk.object.conversation.Topic;
import com.aldebaran.qi.sdk.object.locale.Language;
import com.aldebaran.qi.sdk.object.locale.Locale;
import com.aldebaran.qi.sdk.object.locale.Region;
import com.example.pepperlesson.databinding.ActivityMainBinding;

import java.util.Map;

public class MainActivity extends RobotActivity implements RobotLifecycleCallbacks {

    QiChatbot qiChatbot;
    Chat chat;
    private ActivityMainBinding binding;


    //Bookmark tanımlama alanı
    private Bookmark proposalBookmark; // ilk cümleyi pepperin söylemesi için


    private Bookmark menuBirBookmark; // sayfa geçişleri için menüden aktarma
    private Bookmark menuIkiBookmark;
    private Bookmark menuUcBookmark;
    private Bookmark menuDortBookmark;
    private Bookmark menuBesBookmark;
    private Bookmark menuAltiBookmark;

    //Bookmark Status Tanımlama Alanı
    private BookmarkStatus menuBirStatus;
    private BookmarkStatus menuIkiStatus;
    private BookmarkStatus menuUcStatus;
    private BookmarkStatus menuDortStatus;
    private BookmarkStatus menuBesStatus;
    private BookmarkStatus menuAltiStatus;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);


        // Register the RobotLifecycleCallbacks to this Activity.
        QiSDK.register(this, this);
        binding.cardview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });


        binding.cardview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MainActivity3.class);
                startActivity(intent);

            }
        });
        binding.cardview3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,MainActivity4.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onDestroy() {
        // Unregister the RobotLifecycleCallbacks for this Activity.
        QiSDK.unregister(this, this);
        super.onDestroy();
    }

    @Override
    public void onRobotFocusGained(QiContext qiContext) {
        // The robot focus is gained.


        Locale locale = new Locale(Language.TURKISH, Region.TURKEY);

        // Create a topic.
        Topic topic = TopicBuilder.with(qiContext)
                .withResource(R.raw.menu)
                .build();

// Create a new QiChatbot.
        qiChatbot = QiChatbotBuilder.with(qiContext)
                .withTopic(topic)
                .withLocale(locale)
                .build();

// Create a new Chat action.
         chat = ChatBuilder.with(qiContext)
                .withChatbot(qiChatbot)
                .withLocale(locale)
                .build();



         //Bookmark Alanı
        Map<String, Bookmark> bookmarks = topic.getBookmarks();
        proposalBookmark=bookmarks.get("giris");
        menuBirBookmark=bookmarks.get("menuBir");
        menuIkiBookmark=bookmarks.get("menuIki");
        menuUcBookmark=bookmarks.get("menuUc");
        menuDortBookmark=bookmarks.get("menuDort");
        menuBesBookmark=bookmarks.get("menuBes");
        menuAltiBookmark=bookmarks.get("menuAlti");



        menuBirStatus=qiChatbot.bookmarkStatus(menuBirBookmark);
        menuIkiStatus=qiChatbot.bookmarkStatus(menuIkiBookmark);
        menuUcStatus=qiChatbot.bookmarkStatus(menuUcBookmark);
        menuDortStatus=qiChatbot.bookmarkStatus(menuDortBookmark);
        menuBesStatus=qiChatbot.bookmarkStatus(menuBesBookmark);
        menuAltiStatus=qiChatbot.bookmarkStatus(menuAltiBookmark);




        //Yerleştirdiğimiz bookmarklarda yapmak isteğimiz şeyleri ekleyeceğimiz fonksiyonların gövdesini bu alanda tanımlıyorum

        menuBirStatus.addOnReachedListener(()->{
            runOnUiThread(()->{
                Intent intent=new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            });


        });

        menuIkiStatus.addOnReachedListener(()->{
            runOnUiThread(()->{
                Intent intent=new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
            });

        });
        menuUcStatus.addOnReachedListener(()->{
            runOnUiThread(()->{
                Intent intent=new Intent(MainActivity.this, MainActivity4.class);
                startActivity(intent);

            });
        });









        chat.addOnStartedListener(this::sayfirstproposal);
        chat.async().run();
    }

    @Override
    public void onRobotFocusLost() {
        // The robot focus is lost.
        if (chat != null) {
            chat.removeAllOnStartedListeners();
        }

        // Remove the listeners on each BookmarkStatus.
        if (menuBirStatus != null) {
            menuBirStatus.removeAllOnReachedListeners();
        }
        if (menuIkiStatus != null) {
            menuIkiStatus.removeAllOnReachedListeners();
        }
    }

    @Override
    public void onRobotFocusRefused(String reason) {
        // The robot focus is refused.
    }
    void sayfirstproposal(){
        qiChatbot.goToBookmark(proposalBookmark, AutonomousReactionImportance.HIGH, AutonomousReactionValidity.IMMEDIATE);

    }
}