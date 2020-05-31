package constants;

public enum PieceColor {
    RED("red"), BLUE("blue");

    private String value;

    PieceColor(String value) {
        this.value = value;
    }

    public static PieceColor getEnum(String text){
        for(PieceColor p : PieceColor.values()){
            if(p.value.equalsIgnoreCase(text)){
                return p;
            }
        }
        return null;
    }
}
