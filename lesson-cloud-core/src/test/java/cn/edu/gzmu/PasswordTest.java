package cn.edu.gzmu;

import org.apache.http.client.utils.URIBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.net.URISyntaxException;

public class PasswordTest {
    @Test
    public void name() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
        System.out.println(bCryptPasswordEncoder.encode("1997"));
        System.out.println(bCryptPasswordEncoder.encode("123456"));
        System.out.println(bCryptPasswordEncoder.encode("123456"));
        System.out.println(bCryptPasswordEncoder.encode("123456"));
    }

    @Test
    void uriBuilderTest() throws URISyntaxException {
        URIBuilder builder = new URIBuilder("http://127.0.0.1:8888/oauth/authorize");
        builder
                .addParameter("response_type", "code")
                .addParameter("client_id", "lesson-cloud")
                .addParameter("redirect_uri", "http://127.0.0.1:8888")
                .addParameter("scope", "test");
        System.out.println(builder.build().toString());
        System.out.println(builder.build().toASCIIString());
    }
}
