package xe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xe.xecdApiClient.model.AccountInfoResponse;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface AwsS3Service {

    void uploadAccountInfo(AccountInfoResponse accountInfoResponse) throws IOException;

    AccountInfoResponse downloadAccountInfo() throws IOException;

}
