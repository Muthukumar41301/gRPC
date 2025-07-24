package com.gprc.server_service.service;

import com.gprc.server.VehicleRequest;
import com.gprc.server.VehicleResponse;
import com.gprc.server_service.entity.VehicleDetails;
import com.gprc.server_service.repository.VehicleRepository;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@GrpcService
public class VehicleDetailsServiceImpl extends com.gprc.server.VehicleDetailsGrpc.VehicleDetailsImplBase {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public void getVehicleDetails(VehicleRequest request, StreamObserver<VehicleResponse> responseObserver) {
        VehicleResponse vehicleResponse=null;
        int id=request.getId();
        Optional<VehicleDetails> vehicleDetails=vehicleRepository.findById(id);
        if (vehicleDetails.isPresent()){
            VehicleDetails vehicleDetail=vehicleDetails.get();
            vehicleResponse=VehicleResponse.newBuilder().setId(vehicleDetail.getId())
                    .setVehicleNumber(vehicleDetail.getVehicleNumber())
                    .setVehicleMake(vehicleDetail.getVehicleMake())
                    .setVehicleModel(vehicleDetail.getVehicleModel())
                    .setVehicleVariant(vehicleDetail.getVehicleVariant())
                    .setIsBike(vehicleDetail.getBike()).build();
        }
        responseObserver.onNext(vehicleResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void subscribeVehicle(VehicleRequest request, StreamObserver<VehicleResponse> responseObserver) {

        int id=request.getId();
        try {
            Optional<VehicleDetails> vehicleDetails=vehicleRepository.findById(id);
            if (vehicleDetails.isPresent()) {
                VehicleDetails vehicleDetail = vehicleDetails.get();
                for (int i = 1; i <= 10; i++) {
                    VehicleResponse vehicleResponse = VehicleResponse.newBuilder().setId(vehicleDetail.getId())
                            .setVehicleNumber(vehicleDetail.getVehicleNumber())
                            .setVehicleMake(vehicleDetail.getVehicleMake())
                            .setVehicleModel(vehicleDetail.getVehicleModel())
                            .setVehicleVariant(vehicleDetail.getVehicleVariant())
                            .setIsBike(vehicleDetail.getBike()).build();
                    responseObserver.onNext(vehicleResponse);
                    TimeUnit.SECONDS.sleep(1);
                }
            }
            responseObserver.onCompleted();
        }catch (Exception ex){
            responseObserver.onError(ex);
        }
    }
}
