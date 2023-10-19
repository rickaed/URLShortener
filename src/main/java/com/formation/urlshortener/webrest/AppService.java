package com.formation.urlshortener.webrest;

import com.formation.urlshortener.personalexception.InvalidTokenException;
import com.formation.urlshortener.personalexception.InvalidUrlException;
import com.formation.urlshortener.personalexception.MissingUrlException;
import com.formation.urlshortener.usecase.CreateUrlUseCase;
import com.formation.urlshortener.usecase.DeleteUrlUseCase;
import com.formation.urlshortener.usecase.NewEntityDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

@Service
public class AppService {
    CreateUrlUseCase createUrlUseCase;
    DeleteUrlUseCase deleteUrlUseCase;

    AppService(CreateUrlUseCase createUrlUseCase,
            DeleteUrlUseCase deleteUrlUseCase) {
        this.createUrlUseCase = createUrlUseCase;
        this.deleteUrlUseCase = deleteUrlUseCase;
    }



    public ResponseEntity<?> responseNewUrl(String newUrl, String host)
            throws URISyntaxException, InvalidUrlException, IOException, InterruptedException {
        newUrl = newUrl.replace("\"", "");
        URI newUri = new URI(String.format(newUrl));

        if (createUrlUseCase.check(newUri, host)) {

            System.out.println("@@@@@@@ " + newUri + " à passé tout les test");

            HashMap<String, Object> elemToSend = createUrlUseCase.createUrlEntity(newUri);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .header("X-Removal-Token", (String) elemToSend.get("token"))
                    .body((NewEntityDto) elemToSend.get("entity"));

        }
        return null;
    }

    public HttpStatus responseDeleteUrl(String id, String token) throws IOException {
        System.out.println("@@@@@@@ responseDeleteUrl");
        try {
            deleteUrlUseCase.checkToDelete(id, token);
            System.out.println("@@@@@@@ try check");
            return HttpStatus.NO_CONTENT;
        } catch (MissingUrlException e) {
            System.out.println("@@@@@@@ mauvais ID");
            e.printStackTrace();
            return HttpStatus.NOT_FOUND;
        } catch (InvalidTokenException e) {
            System.out.println("@@@@@@@ mauvais Token");
            e.printStackTrace();
            return HttpStatus.FORBIDDEN;
        }

    }

}
