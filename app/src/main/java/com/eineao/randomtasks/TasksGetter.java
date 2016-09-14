package com.eineao.randomtasks;

import android.util.Log;

import com.eineao.randomtasks.InterestsGetter.Interest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * A class that gets data about Tasks
 * Created by OmarEinea on 12/28/15.
 */
public class TasksGetter {

    public final static List<Task> TASKS = new ArrayList<>();
    public static int tasksNumber = 5, imagesIds[][], pickedTasks[][];
    public static String colors[], titleIds[][], subTitleIds[][];

    public static boolean fillTasks() {

        //If the users disabled all interests then return false
        int favCount = 0;
        for(Interest interest : InterestsGetter.INTERESTS)
            if(interest.mFavored) favCount++;

        switch(favCount) {
            case 0:
                return false;
            case 1:
                tasksNumber = 5;
                MainActivity.toast("Only five tasks are available");
                break;
            case 2:
                tasksNumber = 10;
                MainActivity.toast("Only ten tasks are available");
        }

        if(!TASKS.isEmpty())
            TASKS.clear();
        else
            initializeTasksData();

        final Random random = new Random(new Date().getTime());
        pickedTasks = new int[tasksNumber][3];
        boolean repeatedTask;

        for (int interest, task, color, i = 0; i < tasksNumber; i++) {
            do {
                repeatedTask = false;

                do interest = random.nextInt(10);
                while (!InterestsGetter.INTERESTS.get(interest).mFavored);
                pickedTasks[i][0] = interest;
                Log.i("Interest", "" + interest);

                task = random.nextInt(5);
                pickedTasks[i][1] = task;
                Log.i("Task", "" + task);

                for (int j = 0; j < i; j++)
                    if (pickedTasks[i][0] == pickedTasks[j][0] &&
                            pickedTasks[i][1] == pickedTasks[j][1]) repeatedTask = true;
                Log.i("Repeated Task", "" + repeatedTask);
            } while (repeatedTask);

            do {
                repeatedTask = false;

                color = random.nextInt(colors.length);
                pickedTasks[i][2] = color;

                for (int j = 0; j < i; j++)
                    if (pickedTasks[i][2] == pickedTasks[j][2]) repeatedTask = true;
                Log.i("Repeated Color", "" + repeatedTask);
            } while (repeatedTask);

            TASKS.add((new Task(colors[color], imagesIds[interest][task],
                    titleIds[interest][task], subTitleIds[interest][task])));
        }
        return true;
    }

    private static void initializeTasksData() {
        colors = new String[]
                {"#c62828", "#ad1457", "#6a1b9a", "#4527a0", "#283593", "#1565c0", "#0277bd",
                "#00838f", "#00695c", "#2e7d32", "#558b2f", "#9e9d24", "#f9a825", "#ff8f00",
                "#4e342e", "#424242", "#37474f", "#d50000", "#c51162", "#aa00ff", "#d84315",
                "#6200ea", "#304ffe", "#2962ff", "#0091ea", "#00b8d4", "#00bfa5", "#ef6c00",
                "#00c853", "#64dd17", "#aeea00", "#ffd600", "#ffab00", "#ff6d00", "#dd2c00"};

        subTitleIds = new String[][]
                {{"Roam around while running for about 10 minutes", "Lay on the ground" +
                " face down and start pushing up 5 times with your hands ",
                "Stand up and bend your knees then stand up again for 10 times",
                "Lay on the ground face up and bend until you sit 10 times",
                "Stand up, move one leg forward and the other one backwards 10 times"},
                {"To and Educational recording for at least 5 minutes",
                "Learn a new recipe and cook it by yourself for your family",
                "An Educational lecture about any new topic to you for 10 minutes minimum",
                "A question to refresh what you've learned in the past like math or physics",
                "Attend the first set of any free online course on a website like coursera"},
                {"Make wudu and pray two extra rakat if you've prayed the obligatory ones",
                "Recite at least three papers of quran and understand what they mean",
                "Read one authentic Hadith and understand it and try to memorize it",
                "Do morning or night dhikr depending on the time of the day",
                "Spend some amount of money (as much as you want) on poor people"},
                {"Blindfold yourself for 5 minute and think about the importance of seeing",
                "Cover you ears for 5 minutes and think about the importance of hearing",
                "Think about your family and that you still have them and be thankful",
                "Think about the importance of the functionalities of your body's organs",
                "Think about all the blessings that you're surrounded with and be thankful"},
                {"Message some one you lost contact with and ask them to communicate often",
                "Find something online that'll make you excited because you need it daily",
                "That you know someone dear is in need of and send it to them",
                "Read or watch something to make you laugh or at least make you smile",
                "From online addictions like social media for 1 full hour at least"},
                {"Couple of pages of a book you like or a story, just don't leave reading",
                "Write an article about something you're passionate about or about yourself",
                "A future to-do list of yours, listing them on it's own is good as a start",
                "Something that comes to your mind, even if you don't know how to draw, try",
                "A logo of yours, a poster you wish to see, anything to keep you flexible"},
                {"Go out on a dinner or a lunch alone, it's good every now and then",
                "Grab a whole dish of various fruits and eat it, it's good for your body",
                "Eat a full vegetarian meal that consists of a lot of vegetables",
                "Make some nice drink of boiled herbs and drink it while you're working",
                "For the rest of the day, your body deserves sometime away from them"},
                {"Say hello to a neighbor or to the children down the street with a smile",
                "Say thank you to your wife or mom or a dear friend for being there for you",
                "Say an honest complement to someone around you, and get used to saying it",
                "Say please when you want something, there's nothing wrong in saying it",
                "Say sorry when you do something wrong, it'll wipe problems with people"},
                {"Play a real puzzle or online, it'll stretch your mind and smarten it",
                "Try to solve a riddle, even if takes time, try until you give up on it",
                "There are many apps for mind game, find one and spend 10 minutes with it",
                "Grab a chess board and play with someone of your family and have some fun",
                "If you have an addiction for games like candy crash, stay away forever!"},
                {"Visit your mom and check how is she and ask her about here life and news",
                "Visit someone you've not visited for a long time and check their news",
                "Go out to a new place that you've never been before, explore the world",
                "Visit a family of your extended family, they have the right to see you too",
                "Visit the graveyard and remember that it's where you're gonna end up soon"}};

        titleIds = new String[][]
                {{"Run", "Push Up", "Squat", "Sit Up", "Leg Workout"},
                {"Listen", "Cook", "Watch", "Solve", "Course"},
                {"Pray", "Quran", "Hadith", "Dhikr", "Sadaqa"},
                {"Your Eyes", "Your Ears", "Your Family", "Your Body", "Blessings"},
                {"Message", "Excitement", "Buy a Gift", "Laugh", "Stay Away"},
                {"Read", "Write", "List", "Draw", "Design"},
                {"Alone", "Fruits", "Vegetables", "Herbs", "Avoid SoftDrinks"},
                {"Hello", "Thank you", "Complement", "Please", "Sorry"},
                {"Puzzle", "Riddle", "Mind Game", "Chess", "Avoid Addiction"},
                {"Mom", "Contact Again", "A New Place", "Extended Family", "GraveYard"}};

        imagesIds = new int[][]
                {{R.drawable.run, R.drawable.push_up, R.drawable.squat,
                        R.drawable.set_up, R.drawable.leg},
                {R.drawable.listen, R.drawable.cook, R.drawable.watch,
                        R.drawable.solve, R.drawable.course},
                {R.drawable.pray, R.drawable.quran, R.drawable.hadith,
                        R.drawable.dhikr, R.drawable.sadaqa},
                {R.drawable.no_eyes, R.drawable.no_ears, R.drawable.family,
                        R.drawable.body, R.drawable.blessings},
                {R.drawable.message, R.drawable.excitement, R.drawable.buy_a_gift,
                        R.drawable.laugh, R.drawable.stay_away},
                {R.drawable.read, R.drawable.write, R.drawable.list,
                        R.drawable.draw, R.drawable.design},
                {R.drawable.alone, R.drawable.fruits, R.drawable.vegetables,
                        R.drawable.herbs, R.drawable.avoid_softdrinks},
                {R.drawable.hello, R.drawable.thank_you, R.drawable.complement,
                        R.drawable.please, R.drawable.sorry},
                {R.drawable.puzzle, R.drawable.riddle, R.drawable.mind_games,
                        R.drawable.chess, R.drawable.avoid_addiction},
                {R.drawable.mom, R.drawable.contact_again, R.drawable.new_place,
                        R.drawable.exrended_family, R.drawable.grave_yard}};

    }

    public static class Task extends Interest {

        Task(String color, int imageId, String title, String subTitle) {
            super(color, imageId, title, subTitle);
            mFavored = false;
        }
    }
}