package udd.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import udd.server.model.Rad;
import udd.server.repository.RadRepository;

import java.io.IOException;

@Service
public class RadService {

    @Autowired
    RadRepository radRepository;


    public Rad savePdf(MultipartFile file, Rad rad){
        try {
            rad.setPdf(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return radRepository.save(rad);
    }

}