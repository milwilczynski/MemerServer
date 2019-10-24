package memo.services;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class ImageService {
    private List<String> nameOfFiles = new ArrayList<>();
    private void fillArrayWithNameOfPictures(){
        String projectPath = System.getProperty("user.dir");
        File folder = new File(projectPath+"\\src\\main\\resources\\static\\images");
        File[] listOffFiles = folder.listFiles();
        for(int i = 0; i < listOffFiles.length; i++){
            if(listOffFiles[i].isFile()){
                nameOfFiles.add(listOffFiles[i].getName());
            }
        }
    }
    public String getRandomPicture(){
        fillArrayWithNameOfPictures();
        Random rand = new Random();
        int n = rand.nextInt(nameOfFiles.size());
        return nameOfFiles.get(n);
    }
    public String[] getTenPictures(int page){
        //page = 0 zwroci zdjecia od 0 do 9, page = 1 zwroci zdjecia od 10 do 19, zmienna definiuje zakres obrazkow
        fillArrayWithNameOfPictures();
        String returnArray[] = new String[10];
        int start = 10 * page;
        int end = start + 10;
        if(end > nameOfFiles.size()){
            for(int i = start; i < end; i++){
                returnArray[i] = nameOfFiles.get(i);
            }
            return returnArray;
        }
        return null;
    }
}
