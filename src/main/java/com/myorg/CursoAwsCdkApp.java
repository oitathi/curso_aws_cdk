package com.myorg;

import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.events.targets.SnsTopic;

import java.util.Arrays;

public class CursoAwsCdkApp {
    public static void main(final String[] args) {
        App app = new App();

        VpcStack vpcStack = new VpcStack(app, "vpc");

        ClusterStack clusterStack = new ClusterStack(app,"cluster",vpcStack.getVpc());
        clusterStack.addDependency(vpcStack);

        RdsStack rdsStack = new RdsStack(app, "rds", vpcStack.getVpc());
        rdsStack.addDependency(vpcStack);

        SnsStack snsStack = new SnsStack(app, "sns");


        Service01Stack service01Stack = new Service01Stack(app, "service01", clusterStack.getCluster(), snsStack.getProductEventTopic());
        service01Stack.addDependency(clusterStack);
        service01Stack.addDependency(rdsStack);

        Service02Stack service02Stack = new Service02Stack(app, "service02", clusterStack.getCluster());
        service02Stack.addDependency(clusterStack);

        app.synth();
    }
}

