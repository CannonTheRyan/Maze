import java.util.Timer;
import java.util.TimerTask;

public class TimerClass
{
    private Timer timer;
    private TimerTask task;
    private int seconds;

    public void startTimer()
    {
        seconds = 0;
        timer = new Timer();
        task = new TimerTask(){
            public void run(){
                seconds++;
            }
        };
        timer.scheduleAtFixedRate(task, 500, 1000);
    }

    public void stopTimer()
    {
        timer.cancel();
    }

    public String getTime()
    {
        return "" + seconds / 60 + ":" + ((seconds % 60 < 10)? "0" + seconds%60: seconds%60);
    }

}