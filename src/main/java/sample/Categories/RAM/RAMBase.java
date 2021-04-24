package sample.Categories.RAM;

public class RAMBase {

    private String numeProdus;
    private String Pret;
    private String Descriere;
    private String Tip;
    private String Garantie;
    private int id;

    public RAMBase(){

    }

    public RAMBase(String numeProdus,String Pret,String Descriere,String Tip,String Garantie,int id){
        this.numeProdus=numeProdus;
        this.Pret=Pret;
        this.Descriere=Descriere;
        this.Tip=Tip;
        this.Garantie=Garantie;
        this.id=id;
    }

    public String getDescriere() {
        return Descriere;
    }

    public String getGarantie() {
        return Garantie;
    }

    public String getNumeProdus() {
        return numeProdus;
    }

    public String getPret() {
        return Pret;
    }

    public String getTip() {
        return Tip;
    }
    @Override
    public String toString(){
        return numeProdus + " "+ Pret + " " + Garantie + " " +Tip + " " + Descriere;
    }
}