package sample.DataBase;

import org.dizitart.no2.Nitrite;

import org.dizitart.no2.objects.ObjectFilter;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import sample.Categories.GraphicCards.GraphicCardsBase;
import sample.Categories.Processors.ProcessorsBase;
import sample.Categories.RAM.RAM;
import sample.Categories.RAM.RAMBase;
import sample.Categories.Sources.SourcesBase;
import sample.MainPage.MainPage;
import sample.MainPage.PopUp;
import sample.exceptions.ProductAlreadyExists;

import java.util.Objects;

import static sample.DataBase.FileSystemService.getPathToFile;


public class RAMService {

    private static ObjectRepository<RAMBase> RAMRepository;

    public static void initDataBaseforRAM(){
        Nitrite database = Nitrite.builder()
                .filePath((getPathToFile("RAM.db")).toFile())
                .openOrCreate("test","test");
        RAMRepository= database.getRepository(RAMBase.class);
    }

    public static void addRAM(String numeProdus,String Pret,String Descriere,String Tip,String Garantie,int id) throws ProductAlreadyExists{
        CheckProductAlreadyExists(numeProdus);
        RAMRepository.insert(new RAMBase(numeProdus,Pret,Descriere,Tip,Garantie,id));
    }

    private static void CheckProductAlreadyExists(String name) throws ProductAlreadyExists {
        for(RAMBase ramBase:RAMRepository.find())
        {
            if(Objects.equals(name,ramBase.getNumeProdus()))
            {
                throw new ProductAlreadyExists(name);
            }
        }
    }

    public static void set(){
        for(RAMBase ramBase : RAMRepository.find()){
            RAM.setareVectori(ramBase.getNumeProdus(),ramBase.getPret(),ramBase.getDescriere(),ramBase.getTip(), ramBase.getGarantie());
        }
    }

    public static void DeleteProduct(String numeProdus){
        for(RAMBase ramBase : RAMRepository.find()){
            if(numeProdus.equals(ramBase.getNumeProdus())){
                RAMRepository.remove(ObjectFilters.eq("numeProdus",numeProdus));
            }
        }
    }

    public static void EditProduct(String numeProdus,String Pret,String Tip,String Garantie,String Descriere) {
        for(RAMBase ramBase : RAMRepository.find())
        {
            if (numeProdus.equals(ramBase.getNumeProdus())) {
                ramBase.setDescriere(Descriere);
                ramBase.setPret(Pret);
                ramBase.setGarantie(Garantie);
                ramBase.setTip(Tip);
                DeleteProduct(numeProdus);
                RAMRepository.insert(ramBase);
            }
        }
    }

    public static void setForDelete(){
        for(RAMBase ramBase : RAMRepository.find()){
            if(UserService.returnId(MainPage.getUsernameFromMain())==ramBase.getId()){
                PopUp.getDataBase(ramBase.getNumeProdus(),ramBase.getPret(),ramBase.getDescriere(),ramBase.getTip(), ramBase.getGarantie());
            }
        }
    }


}

