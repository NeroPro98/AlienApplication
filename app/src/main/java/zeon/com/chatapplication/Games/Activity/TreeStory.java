package zeon.com.chatapplication.Games.Activity;

public class TreeStory {

    private int que;
    private int ans1;
    private int ans2;



    public TreeStory(int que, int ans1, int ans2) {
        this.que = que;
        this.ans1 = ans1;
        this.ans2 = ans2;
    }

    public int getQue() {
        return que;
    }

    public int getAns1() {
        return ans1;
    }

    public int getAns2() {
        return ans2;
    }

    public void setQue(int que) {
        this.que = que;
    }

    public void setAns1(int ans1) {
        this.ans1 = ans1;
    }

    public void setAns2(int ans2) {
        this.ans2 = ans2;
    }
}
