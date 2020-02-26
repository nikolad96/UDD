package udd.server.dto;

public class MoreLikeThisDTO {
    private String vrednost;
    private int min;
    private int max;

    public MoreLikeThisDTO(String vrednost, int min, int max) {
        this.vrednost = vrednost;
        this.min = min;
        this.max = max;
    }

    public MoreLikeThisDTO() {
    }

    public String getVrednost() {
        return vrednost;
    }

    public void setVrednost(String vrednost) {
        this.vrednost = vrednost;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
