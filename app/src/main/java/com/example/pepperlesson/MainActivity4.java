package com.example.pepperlesson;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

import com.aldebaran.qi.Future;
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.builder.AnimateBuilder;
import com.aldebaran.qi.sdk.builder.AnimationBuilder;
import com.aldebaran.qi.sdk.builder.HolderBuilder;
import com.aldebaran.qi.sdk.design.activity.RobotActivity;
import com.aldebaran.qi.sdk.object.actuation.Animate;
import com.aldebaran.qi.sdk.object.actuation.Animation;
import com.aldebaran.qi.sdk.object.holder.AutonomousAbilitiesType;
import com.aldebaran.qi.sdk.object.holder.Holder;
import com.example.pepperlesson.databinding.ActivityMain4Binding;


public class MainActivity4 extends RobotActivity implements RobotLifecycleCallbacks {

    ActivityMain4Binding binding;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Register the RobotLifecycleCallbacks to this Activity.
        binding=ActivityMain4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        QiSDK.register(this, this);
        mediaPlayer= MediaPlayer.create(this,R.raw.harman);

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
        Holder holder = HolderBuilder.with(qiContext)
                .withAutonomousAbilities(AutonomousAbilitiesType.BACKGROUND_MOVEMENT,AutonomousAbilitiesType.BASIC_AWARENESS)
                .build();
        // Release the ability asynchronously.



        // Create an animation object.
        Animation myAnimation = AnimationBuilder.with(qiContext)
                .withResources(R.raw.dans)
                .build();

// Build the action.
        Animate animate = AnimateBuilder.with(qiContext)
                .withAnimation(myAnimation)
                .build();

        Animation myAnimation2 = AnimationBuilder.with(qiContext)
                .withResources(R.raw.yurume1)
                .build();

// Build the action.
        Animate animate2 = AnimateBuilder.with(qiContext)
                .withAnimation(myAnimation2)
                .build();

        Animation myAnimation3 = AnimationBuilder.with(qiContext)
                .withResources(R.raw.yavaskolinme)
                .build();

// Build the action.
        Animate animate3 = AnimateBuilder.with(qiContext)
                .withAnimation(myAnimation3)
                .build();

        Animation myAnimation4 = AnimationBuilder.with(qiContext)
                .withResources(R.raw.donmesag)
                .build();
// Build the action.
        Animate animate4 = AnimateBuilder.with(qiContext)
                .withAnimation(myAnimation4)
                .build();



        Future<Void> animateFuture=animate.async().run();

        animate.addOnStartedListener(()->{
            mediaPlayer.start();
        });

        holder.async().hold();

        animateFuture.andThenConsume(ignore ->{

            Future<Void> animateFuture2=animate2.async().run();

            holder.async().hold();
            animateFuture2.andThenConsume(ignore2->{
                Future<Void> animateFuture3=animate3.async().run();
                holder.async().hold();
            animateFuture3.andThenConsume(ignore3->{
                Future<Void> animateFuture4=animate4.async().run();
                holder.async().hold();
                animateFuture4.andThenConsume( ignore4 ->{
                    Future<Void> animateFuture5=animate4.async().run();
                    holder.async().hold();
                    animateFuture5.andThenConsume(ignore5 ->{
                        Future<Void> animateFuture6=animate.async().run();
                        holder.async().hold();
                        animateFuture6.andThenConsume(ignore6 ->{
                            Future<Void> animateFuture7=animate2.async().run();
                            holder.async().hold();
                            animateFuture7.andThenConsume(ignore7 ->{
                                Future<Void> animateFuture8=animate3.async().run();
                                holder.async().hold();
                                animateFuture8.andThenConsume(ignore8 ->{
                                    Future<Void> animateFuture9=animate4.async().run();
                                    holder.async().hold();
                                });
                            });

                        });
                    });

                });
            });

            });

        });


    }

    @Override
    public void onRobotFocusLost() {
        // The robot focus is lost.
    }

    @Override
    public void onRobotFocusRefused(String reason) {
        // The robot focus is refused.
    }
}