package org.example;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.GetBucketLocationRequest;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String bucketName = "ujjwalkumar123";
        String stringObjKeyName = "object-name";
        String fileObjKeyName = "object-name";
        String fileName = "";
       // String bucketName = "filebase-bucket-name";

        try {
            AmazonS3ClientBuilder.standard();
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withCredentials(new ProfileCredentialsProvider())
                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("s3.filebase.com", "us-east-1"))
                    .build();

              if (!s3Client.doesBucketExistV2(bucketName)) {
                s3Client.createBucket(new CreateBucketRequest(bucketName));

                String bucketLocation = s3Client.getBucketLocation(new GetBucketLocationRequest(bucketName));
                System.out.println("Bucket location: " + bucketLocation);
            }
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }


    }
}



