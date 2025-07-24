package com.gprc.client_service.service;

import com.gprc.server.VehicleDetailsGrpc;
import com.gprc.server.VehicleRequest;
import com.gprc.server.VehicleResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl {

    @GrpcClient("vehicleService")
    private VehicleDetailsGrpc.VehicleDetailsStub vehicleDetailsStub;

//    public VehicleResponse getVehicleDetails(int id) {
//        VehicleRequest request = VehicleRequest.newBuilder().setId(id).build();
//        return vehicleDetailsStub.getVehicleDetails(request);
//    }

    public void subscribeVehicle(int id){
        VehicleRequest request=VehicleRequest.newBuilder().setId(id).build();
        vehicleDetailsStub.subscribeVehicle(request, new StreamObserver<VehicleResponse>() {
            @Override
            public void onNext(VehicleResponse vehicleResponse) {
                System.out.println(vehicleResponse.getVehicleNumber());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error : " + throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("vehicle details update completed !");
            }
        });
    }
}
