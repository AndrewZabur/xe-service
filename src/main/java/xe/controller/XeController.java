package xe.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xe.xecdApiClient.exception.XecdApiException;
import com.xe.xecdApiClient.model.AccountInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xe.service.AwsS3Service;
import xe.service.XeService;

import java.io.IOException;

@RestController
@RequestMapping("xe/")
public class XeController {

    private final XeService xeService;
    private final AwsS3Service awsS3Service;

    @Autowired
    public XeController(XeService xeService, AwsS3Service awsS3Service) {
        this.xeService = xeService;
        this.awsS3Service = awsS3Service;
    }

    @GetMapping("account-info")
    public ResponseEntity<AccountInfoResponse> getAccountInfo() throws XecdApiException, IOException {
        return ResponseEntity.ok(xeService.getAccountInfo());
    }

    @GetMapping("account-info-from-s3")
    public ResponseEntity<AccountInfoResponse> getAccountInfoFromS3() throws IOException {
        return ResponseEntity.ok(awsS3Service.downloadAccountInfo());
    }
}
