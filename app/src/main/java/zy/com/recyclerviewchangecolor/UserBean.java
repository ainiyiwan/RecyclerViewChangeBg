package zy.com.recyclerviewchangecolor;

/**
 * ================================================
 * 作    者：Luffy（张阳）
 * 版    本：1.0
 * 创建日期：2018/5/12
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class UserBean {

    private boolean leftShow;
    private boolean rightShow;

    public boolean isLeftShow() {
        return leftShow;
    }

    public void setLeftShow(boolean leftShow) {
        this.leftShow = leftShow;
    }

    public boolean isRightShow() {
        return rightShow;
    }

    public void setRightShow(boolean rightShow) {
        this.rightShow = rightShow;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "leftShow=" + leftShow +
                ", rightShow=" + rightShow +
                '}';
    }
}
