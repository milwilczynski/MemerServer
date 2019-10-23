package memo.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
}
