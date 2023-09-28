package com.example.pepperlesson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.aldebaran.qi.Future;
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.builder.AnimateBuilder;
import com.aldebaran.qi.sdk.builder.AnimationBuilder;
import com.aldebaran.qi.sdk.builder.ChatBuilder;
import com.aldebaran.qi.sdk.builder.QiChatbotBuilder;
import com.aldebaran.qi.sdk.builder.TopicBuilder;
import com.aldebaran.qi.sdk.design.activity.RobotActivity;
import com.aldebaran.qi.sdk.object.actuation.Animate;
import com.aldebaran.qi.sdk.object.actuation.Animation;
import com.aldebaran.qi.sdk.object.conversation.Bookmark;
import com.aldebaran.qi.sdk.object.conversation.BookmarkStatus;
import com.aldebaran.qi.sdk.object.conversation.Chat;
import com.aldebaran.qi.sdk.object.conversation.QiChatbot;
import com.aldebaran.qi.sdk.object.conversation.Topic;
import com.aldebaran.qi.sdk.object.locale.Language;
import com.aldebaran.qi.sdk.object.locale.Locale;
import com.aldebaran.qi.sdk.object.locale.Region;
import com.example.pepperlesson.databinding.ActivityMain3Binding;

import java.util.Map;

public class MainActivity3 extends RobotActivity implements RobotLifecycleCallbacks {

    ActivityMain3Binding binding;
    private Animate animate;

    QiChatbot qiChatbot;
    Chat chat;



    //Bookmark tanımlama alanı
    private Bookmark onegitBookmark;
    private Bookmark arkayagitBokkmark;
    private Bookmark sagagitBookmark;
    private Bookmark solagitBookmark;


    private BookmarkStatus onegitStatus;
    private BookmarkStatus arkayagitStatus;
    private BookmarkStatus sagagitStatus;
    private BookmarkStatus solagitStatus;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMain3Binding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        // Register the RobotLifecycleCallbacks to this Activity.
        QiSDK.register(this, this);
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
                .withResource(R.raw.hareket)
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


        Map<String, Bookmark> bookmarks = topic.getBookmarks();
        onegitBookmark=bookmarks.get("on");
        arkayagitBokkmark=bookmarks.get("arka");
        sagagitBookmark=bookmarks.get("sag");
        solagitBookmark=bookmarks.get("sol");

        onegitStatus=qiChatbot.bookmarkStatus(onegitBookmark);
        arkayagitStatus=qiChatbot.bookmarkStatus(arkayagitBokkmark);
        sagagitStatus=qiChatbot.bookmarkStatus(sagagitBookmark);
        solagitStatus=qiChatbot.bookmarkStatus(solagitBookmark);



        onegitStatus.addOnReachedListener(()->{
            // Create an animation.
            Animation animation = AnimationBuilder.with(qiContext) // Create the builder with the context.
                    .withResources(R.raw.trajectory_00) // Set the animation resource.
                    .build(); // Build the animation.

            // Create an animate action.
            animate = AnimateBuilder.with(qiContext) // Create the builder with the context.
                    .withAnimation(animation) // Set the animation.
                    .build(); // Build the animate action.

            Future<Void> animateFuture = animate.async().run();


        });

        arkayagitStatus.addOnReachedListener(()->{
            // Create an animation.
            Animation animation = AnimationBuilder.with(qiContext) // Create the builder with the context.
                    .withResources(R.raw.trajectory_01) // Set the animation resource.
                    .build(); // Build the animation.

            // Create an animate action.
            animate = AnimateBuilder.with(qiContext) // Create the builder with the context.
                    .withAnimation(animation) // Set the animation.
                    .build(); // Build the animate action.

            Future<Void> animateFuture = animate.async().run();

        });


        sagagitStatus.addOnReachedListener(()->{
            // Create an animation.
            Animation animation = AnimationBuilder.with(qiContext) // Create the builder with the context.
                    .withResources(R.raw.trajectory_02) // Set the animation resource.
                    .build(); // Build the animation.

            // Create an animate action.
            animate = AnimateBuilder.with(qiContext) // Create the builder with the context.
                    .withAnimation(animation) // Set the animation.
                    .build(); // Build the animate action.

            Future<Void> animateFuture = animate.async().run();

        });

        solagitStatus.addOnReachedListener(()->{
            // Create an animation.
            Animation animation = AnimationBuilder.with(qiContext) // Create the builder with the context.
                    .withResources(R.raw.trajectory_03) // Set the animation resource.
                    .build(); // Build the animation.

            // Create an animate action.
            animate = AnimateBuilder.with(qiContext) // Create the builder with the context.
                    .withAnimation(animation) // Set the animation.
                    .build(); // Build the animate action.

            Future<Void> animateFuture = animate.async().run();

        });

        chat.async().run();




    }

    @Override
    public void onRobotFocusLost() {
        // The robot focus is lost.
        if(onegitStatus!=null){
            chat.removeAllOnStartedListeners();
        }
        if(arkayagitStatus!=null){
            chat.removeAllOnStartedListeners();
        }
        if(sagagitStatus!=null){
            chat.removeAllOnStartedListeners();
        }
        if(solagitStatus!=null){
            chat.removeAllOnStartedListeners();
        }
    }

    @Override
    public void onRobotFocusRefused(String reason) {
        // The robot focus is refused.
    }
}