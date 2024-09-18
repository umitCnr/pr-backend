package com.pr.project_backend.firebase;

import com.google.api.client.util.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class FirebaseConfig {

    @Value("classpath:/serviceAccountKey.json")
    private Resource privateKey;



}
