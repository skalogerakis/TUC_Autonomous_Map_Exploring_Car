#pragma config(Sensor, S1,     rightLight,     sensorLightActive)
#pragma config(Sensor, S2,     Colour,         sensorCOLORFULL)
#pragma config(Sensor, S3,     leftLight,      sensorLightActive)
#pragma config(Motor,  motorA,          rightMotor,    tmotorNormal, PIDControl, encoder)
#pragma config(Motor,  motorC,          leftMotor,     tmotorNormal, PIDControl, encoder)

//Code for wandering in a random maze
//The idea is to contain the black line between the two light sensors,
//and depending on the rgb sensor to pick if we will turn or not, in case of a junction.

task main()
{
int right_left=0;
int termination_flag=0;

 while(termination_flag == 0){
   switch (SensorValue[Colour]){
    case REDCOLOR:               //In case of red colour, we terminate
		    termination_flag=1;
		break;
    case BLACKCOLOR:            //In case of black colour, we are on the line, continuing straight
    while (SensorValue(leftLight)>30 && SensorValue(rightLight)>40 ){
        motor[leftMotor] = 30;
				motor[rightMotor] = 30;
	  }
	  break;

	   case WHITECOLOR:
	   //In case of white colour, we are forced to go left or right
	   //If both options are available, we pick right the first time, left the second etc. (Specific Choices just for the Demo)
	   if (SensorValue(leftLight)<30 && SensorValue(rightLight)<40 ){
	      right_left++;
	      if (right_left%2 == 0){
	         while (SensorValue(leftLight)< 30){
	            motor[leftMotor] = -5;
				      motor[rightMotor] = 30;
		       }
		     }else{
		       	while (SensorValue(rightLight)< 40){
	            motor[leftMotor] = 30;
				      motor[rightMotor] = -5;
			      }
			    }

		  }
		  else{

	    while (SensorValue(leftLight)< 30){       //If only left turn is available, turn left
	      motor[leftMotor] = -5;
				motor[rightMotor] = 30;
		  }
			while (SensorValue(rightLight)< 40){      //If only right turn is available, turn right
	      motor[leftMotor] = 30;
				motor[rightMotor] = -5;
			}
		}
		break;

		}
	}
}




