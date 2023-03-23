package tech.salvas.eifapi.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class FileServiceImplementation implements FileService {

    final AmazonS3 s3Client;

    @Value("${do.space.bucket}")
    private String spaceBucket;

    String folder = "activities-files/";

    public FileServiceImplementation(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public void save(MultipartFile multipartFile, String activityID) throws IOException {

        System.out.println(spaceBucket);

        String key = folder + activityID + "/" + multipartFile.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();

        metadata.setContentLength(multipartFile.getInputStream().available());

        if (multipartFile.getContentType() != null && !"".equals(multipartFile.getContentType())) {
            metadata.setContentType(multipartFile.getContentType());
        }

        s3Client.putObject(new PutObjectRequest(spaceBucket, key, multipartFile.getInputStream(), metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        // save file info to repository and database
    }

    @Override
    public void delete(int id) throws Exception {

    }

    @Override
    public List<File> getAll() {
        return null;
    }

    @Override
    public File get(int id) {
        return null;
    }
}
