package com.myorg;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.ec2.Vpc;
import software.constructs.Construct;
// import software.amazon.awscdk.Duration;
// import software.amazon.awscdk.services.sqs.Queue;

public class VpcStack extends Stack {

    private Vpc vpc;

    public VpcStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public VpcStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        vpc =Vpc.Builder.create(this,"vpc01")
                .maxAzs(3) //zonas de diponibilidade
                .build();
    }

    public Vpc getVpc() {
        return vpc;
    }
}
