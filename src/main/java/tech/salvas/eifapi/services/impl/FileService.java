package tech.salvas.eifapi.services.impl;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.salvas.eifapi.models.File;
import tech.salvas.eifapi.services.IFileService;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService implements IFileService {
    final AmazonS3 s3Client;
    String folder = "activities-files/";
    @Value("${do.space.bucket}")
    private String spaceBucket;

    public FileService(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public void save(MultipartFile multipartFile, String activityID) throws IOException {
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
    public void delete(String activityID, String key) {
        s3Client.deleteObject(new DeleteObjectRequest(spaceBucket, folder + activityID + "/" + key));
    }

    @Override
    public List<File> getAll(String activityID) {
        ListObjectsRequest request = new ListObjectsRequest().withBucketName(spaceBucket).withPrefix(folder + activityID + "/");

        ObjectListing response = s3Client.listObjects(request);
        List<S3ObjectSummary> objects = response.getObjectSummaries();

        return objects.stream().map(
                object -> new File(
                        object.getKey(),
                        Long.toString(object.getSize()),
                        object.getLastModified().toString())
        ).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public String get(String activityID, String key) {
        Date expiration = new Date();
        long expTimeMillis = Instant.now().toEpochMilli();
        expTimeMillis += 1000 * 60 * 2; // now + 2m
        expiration.setTime(expTimeMillis);

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(spaceBucket, folder + activityID + "/" + key)
                        .withMethod(HttpMethod.GET)
                        .withExpiration(expiration);
        URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);

        return url.toString();
    }
}
