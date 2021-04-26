package sample.DataBase;
import org.dizitart.no2.Nitrite;
import sample.Categories.Processors.Processors;
import sample.Categories.Processors.ProcessorsBase;

import org.dizitart.no2.objects.ObjectRepository;
import sample.MainPage.MainPage;
import sample.MainPage.PopUp;

import static sample.DataBase.FileSystemService.getPathToFile;


public class ProcessorsService {

    private static ObjectRepository<ProcessorsBase> ProcessorsRepository;

    public static void initDataBaseforProcessors(){
        Nitrite database = Nitrite.builder()
                .filePath((getPathToFile("Processors.db")).toFile())
                .openOrCreate("test","test");
        ProcessorsRepository= database.getRepository(ProcessorsBase.class);
    }

    public static void set()
    {
        for(ProcessorsBase processorsBase : ProcessorsRepository.find())
        {
            Processors.Test(processorsBase.getNumeProdus(),processorsBase.getPret(),processorsBase.getDescriere(),processorsBase.getTip(), processorsBase.getGarantie());
        }

    }

    public static void setForDelete(){
        for(ProcessorsBase processorsBase : ProcessorsRepository.find())
        {
            if(UserService.returnId(MainPage.getUsernameFromMain()) == processorsBase.getId()){
                PopUp.getDataBase(processorsBase.getNumeProdus(),processorsBase.getPret(),processorsBase.getDescriere(),processorsBase.getTip(), processorsBase.getGarantie());
            }
        }
    }

    public static void addProcessors(String numeProdus,String Pret,String Descriere,String Tip,String Garantie,int id){
        ProcessorsRepository.insert(new ProcessorsBase(numeProdus,Pret,Descriere,Tip,Garantie,id));
    }
}
