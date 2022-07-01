package xe.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xe.xecdApiClient.exception.XecdApiException;
import com.xe.xecdApiClient.model.AccountInfoResponse;
import com.xe.xecdApiClient.service.XecdApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xe.service.AwsS3Service;
import xe.service.XeService;

import java.io.IOException;

@Service
public class XeServiceImpl implements XeService {

    private final XecdApiService xecdApiService;
    private final AwsS3Service awsS3Service;

    @Autowired
    public XeServiceImpl(XecdApiService xecdApiService, AwsS3Service awsS3Service) {
        this.xecdApiService = xecdApiService;
        this.awsS3Service = awsS3Service;
    }

    @Override
    public AccountInfoResponse getAccountInfo() throws XecdApiException, IOException {
        AccountInfoResponse accountInfoResponse = xecdApiService.getAccountInfo();
        awsS3Service.uploadAccountInfo(accountInfoResponse);
        return accountInfoResponse;
    }
}
