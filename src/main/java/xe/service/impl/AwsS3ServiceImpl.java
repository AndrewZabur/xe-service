package xe.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xe.xecdApiClient.model.AccountInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xe.service.AwsS3Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;

@Service
public class AwsS3ServiceImpl implements AwsS3Service {

    private final AmazonS3 amazonS3;
    private final ObjectMapper objectMapper;
    private static final String BUCKET_NAME = "bucket-for-account-info";


    @Autowired
    public AwsS3ServiceImpl(AmazonS3 amazonS3, ObjectMapper objectMapper) {
        this.amazonS3 = amazonS3;
        this.objectMapper = objectMapper;
    }

    @Override
    public void uploadAccountInfo(AccountInfoResponse accountInfoResponse) throws IOException {
        String currentDate = LocalDate.now().toString();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType("application/json");

        PutObjectRequest request = new PutObjectRequest(BUCKET_NAME, "account-info/" + currentDate + "/accountInfo.json",
                new ByteArrayInputStream(objectMapper.writeValueAsBytes(accountInfoResponse)), objectMetadata);
        amazonS3.putObject(request);
    }

    @Override
    public AccountInfoResponse downloadAccountInfo() throws IOException {
        String currentDate = LocalDate.now().toString();
        S3Object obj = amazonS3.getObject(BUCKET_NAME, "account-info/" + currentDate + "/accountInfo.json");
        return objectMapper.readValue(obj.getObjectContent(), AccountInfoResponse.class);
    }
}
