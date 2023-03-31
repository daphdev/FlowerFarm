package entity;

public class User extends Register {
    private int level;
    private int exp;
    private int coin;
    private int plotNum;
    private String cellNum;

    public User() {}

    public User(Register register) {
        super(register.id, register.username, register.password);
        this.level = 0;
        this.exp = 0;
        this.coin = 15;
        this.plotNum = 1;
        this.cellNum = "0";
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getPlotNum() {
        return plotNum;
    }

    public void setPlotNum(int plotNum) {
        this.plotNum = plotNum;
    }

    public String getCellNum() {
        return cellNum;
    }

    public void setCellNum(String cellNum) {
        this.cellNum = cellNum;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", level=" + level + ", exp="
                + exp + ", coin=" + coin + ", plotNum=" + plotNum + ", cellNum=" + cellNum + "]";
    }
}
