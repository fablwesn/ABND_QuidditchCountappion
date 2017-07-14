package com.fablwesn.seventoes.quidditchcountappion;
/**
 *|‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|
 *| Darijo Barucic                                                                                |
 *| fablwesn@gmail.com                                                                            |
 *| Vienna, Austria                                                                               |
 *|_______________________________________________________________________________________________|
 *|                                                                                               |
 *| I wanted to see, if there is a possibility to change the image of an ImageView at runtime and |
 *| didn't really manage to make it work and some looked to complicated, I didn't even try.       |
 *| But i found out about the ImageSwitcher - exactly what I wanted.                              |
 *| It was no easy task either, but pretty easy to implant, thanks to some bloggers.              |
 *| I found different resources and I decided to follow the one at:                               |
 *|                                                                                               |
 *|| |   http://android-er.blogspot.co.at/2013/11/android-example-imageswitcher.html           | ||
 *|                                                                                               |
 *| The article is from 2013 but it works fine (guess it'll work on older devices too).           |
 *| Has anything changed in the way of setting up an ImageSwitcher? I found the part about        |
 *| setting the factory pretty confusing, the only thing I think I haven't understood completely. |
 *| But it's too soon for me I guess..I'll write it down for later :D                             |
 *|                                                                                               |
 *| The rest went pretty smoothly. :)                                                             |
 *|                                                                                               |
 *| Thanks for reviewing!                                                                         |
 *|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends AppCompatActivity {

    // for the two ImageSwitchers and Buttons in the layout
    ImageSwitcher teamA;
    ImageSwitcher teamB;
    Button nextTeamA;
    Button nextTeamB;

    // an array holding all possible images, the image view can toggle through
    int teamAImages[] = {R.drawable.gryffindor_herald,
            R.drawable.hufflepuff_herald,
            R.drawable.ravenclaw_herald,
            R.drawable.slytherin_herald};
    int teamBImages[] = {R.drawable.gryffindor_herald,
            R.drawable.hufflepuff_herald,
            R.drawable.ravenclaw_herald,
            R.drawable.slytherin_herald};

    // used to count through the index of the teamImage arrays, thus being
    // able to select which one to show
    int counterA;
    int counterB;

    // variables holding the current score of the game
    int score_team_a = 0;
    int score_team_b = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayScores(); //display 0 - 0 in the beginning

        // fill the variables with their components, found by their id
        teamA = (ImageSwitcher) findViewById(R.id.team_a);
        teamB = (ImageSwitcher) findViewById(R.id.team_b);
        nextTeamA = (Button) findViewById(R.id.next_team_a);
        nextTeamB = (Button) findViewById(R.id.next_team_b);

        // factoring for both teams. I'm not pretty sure what's this all about, what is
        // needed and what else could be done here.
        // but as I understood it, it just creates an ImageView to be stored in the
        // ImageSwitcher and sets its parameters.
        teamA.setFactory(new ViewFactory() {
            @Override
            public View makeView() {
                ImageView teamAImageView = new ImageView(MainActivity.this);
                teamAImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                teamAImageView.setAdjustViewBounds(true);

                LayoutParams params = new ImageSwitcher.LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

                teamAImageView.setLayoutParams(params);
                return teamAImageView;
            }
        });
        teamB.setFactory(new ViewFactory() {
            @Override
            public View makeView() {
                ImageView teamBImageView = new ImageView(MainActivity.this);
                teamBImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                teamBImageView.setAdjustViewBounds(true);

                LayoutParams params = new ImageSwitcher.LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

                teamBImageView.setLayoutParams(params);
                return teamBImageView;
            }
        });

        // starting value for the counter. counterB is at 3 so that the default team for Team B
        // is slytherin. changeable to any number from 0-3 (teamImage arrays length)
        counterA = 0;
        counterB = 3;

        // set the image to be displayed, depending on the order in the array and the counters value
        teamA.setImageResource(teamAImages[counterA]);
        teamB.setImageResource(teamBImages[counterB]);

        // manages the toggling between the images, when the button is pressed.
        //if the counter is bigger than the length of the array, set the counter to zero
        //to start from the first image again - .length -1 because arrays start at 0.
        //if not, increase the counter by one, displaying the next image in order
        nextTeamA.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (counterA == teamAImages.length - 1) {
                    counterA = 0;
                    teamA.setImageResource(teamAImages[counterA]);
                } else {
                    teamA.setImageResource(teamAImages[++counterA]);
                }
            }
        });
        nextTeamB.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (counterB == teamBImages.length - 1) {
                    counterB = 0;
                    teamB.setImageResource(teamBImages[counterB]);
                } else {
                    teamB.setImageResource(teamBImages[++counterB]);
                }
            }
        });

        // animation for the switching between images
        Animation animationOut = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        Animation animationIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);

        teamA.setOutAnimation(animationOut);
        teamA.setInAnimation(animationIn);

        teamB.setOutAnimation(animationOut);
        teamB.setInAnimation(animationIn);


    }

    // only display Team A's current score
    private void displayScoreA(int number) {
        TextView scoreA = (TextView) findViewById(R.id.team_a_score_txt);
        scoreA.setText(String.valueOf(number));
    }

    // only display Team A's current score
    private void displayScoreB(int number) {
        TextView scoreB = (TextView) findViewById(R.id.team_b_score_txt);
        scoreB.setText(String.valueOf(number));
    }

    // display current values of the score variables on the screen
    private void DisplayScores() {
        displayScoreA(score_team_a);
        displayScoreB(score_team_b);
    }

    // after the goal button for Team A is pressed, adds 10 to Team A's score variable
    public void GoalTeamA(View view) {
        score_team_a = score_team_a + 10;
        displayScoreA(score_team_a);
    }

    // after the goal button for Team B is pressed, adds 10 to Team B's score variable
    public void GoalTeamB(View view) {
        score_team_b = score_team_b + 10;
        displayScoreB(score_team_b);
    }

    // after the snitch button for Team A is pressed, adds 150 to Team A's score variable
    public void SnitchTeamA(View view) {
        score_team_a = score_team_a + 150;
        displayScoreA(score_team_a);
    }

    // after the snitch button for Team B is pressed, adds 150 to Team A's score variable
    public void SnitchTeamB(View view) {
        score_team_b = score_team_b + 150;
        displayScoreB(score_team_b);
    }

    // set's both score variables to zero and displays them
    public void ResetScore(View view) {
        score_team_a = 0;
        score_team_b = 0;
        DisplayScores();
    }
}
