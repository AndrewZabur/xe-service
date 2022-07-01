package xe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xe.xecdApiClient.exception.XecdApiException;
import com.xe.xecdApiClient.model.AccountInfoResponse;

import java.io.IOException;

public interface XeService {

    AccountInfoResponse getAccountInfo() throws XecdApiException, IOException;

}
