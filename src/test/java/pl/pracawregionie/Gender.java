package pl.pracawregionie;

public enum Gender {
    FEMALE,MALE,FEMALE2,MALE2;

    @Override
    public String toString(){
        switch (this){
            case MALE: return "Pan";
            case FEMALE: return "Pani";
            case MALE2: return "0";
            case FEMALE2: return "1";
            default: return "b.d.";
        }
    }
    
}
