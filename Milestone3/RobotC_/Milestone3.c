#pragma config(Sensor, S1,     rightLight,     sensorLightActive)
#pragma config(Sensor, S2,     Colour,         sensorCOLORFULL)
#pragma config(Sensor, S3,     leftLight,      sensorLightActive)
#pragma config(Motor,  motorA,          rightMotor,    tmotorNormal, PIDControl, encoder)
#pragma config(Motor,  motorC,          leftMotor,     tmotorNormal, PIDControl, encoder)

void dfs(int row,int col);
int visit_matrix[20][20];  //nodes visited
int temp_map[20][20];      //1 for path 0 for walls
int final_map[10][10];     //final 10x10 maze
int i,j;
int d=0;
int m=0;
int count=0;
int row= 10;
int col= 10;
void forward(){
	int distance = 10;
	int radius = 2.7;
	while (SensorValue(leftLight)>30 && SensorValue(rightLight)>40 ){
        nMotorEncoder[leftMotor] = 0;
				nMotorEncoder[rightMotor] = 0;
				nMotorEncoderTarget[leftMotor] = (distance*360)/(2*3.14*radius);  //encoder for standard movement
		    nMotorEncoderTarget[rightMotor] = (distance*360)/(2*3.14*radius);
        motor[leftMotor] = 30;
        motor[rightMotor] = 30;
	  }
}

void left_turn(int d){
	int distance = 1;
	int radius = 2.7; //wheel radius
	while (SensorValue(leftLight)>30 && SensorValue(rightLight)>40){
	      motor[leftMotor] = -30;
				motor[rightMotor] = 30;
	}
	nMotorEncoder[leftMotor] = 0;
	nMotorEncoder[rightMotor] = 0;
	nMotorEncoderTarget[leftMotor] = - (distance*360)/(2*3.14*radius);  //encoder for standard movement
	nMotorEncoderTarget[rightMotor] = - (distance*360)/(2*3.14*radius);
	if (d==0) d=1;    //0:up 1:left 2:right 3:down
  if (d==1) d=3;
  if (d==2) d=0;
  if (d==3) d=2;
}

void right_turn(int d){
	int distance = 1;
	int radius = 2.7; //wheel radius
	while (SensorValue(leftLight)>30 && SensorValue(rightLight)>40){
	      motor[leftMotor] = 30;
				motor[rightMotor] = -30;
	}
	nMotorEncoder[leftMotor] = 0;
	nMotorEncoder[rightMotor] = 0;
	nMotorEncoderTarget[leftMotor] = - (distance*360)/(2*3.14*radius);  //encoder for standard movement
	nMotorEncoderTarget[rightMotor] = - (distance*360)/(2*3.14*radius);
	if (d==0) d=2;
  if (d==1) d=0;
 	if (d==2) d=3;
  if (d==3) d=1;
}

void full_turn(int d){
	int distance = 4;
	int radius = 2.7;
	while (SensorValue(rightLight)< 40){     //full turn to the left in case of deadend
	     	motor[leftMotor] = -30;
				motor[rightMotor] = 30;
	}
	nMotorEncoder[leftMotor] = 0;
	nMotorEncoder[rightMotor] = 0;
	nMotorEncoderTarget[leftMotor] = - (distance*360)/(2*3.14*radius);  //encoder for standard movement
	nMotorEncoderTarget[rightMotor] = - (distance*360)/(2*3.14*radius);
	if (d==0) d=3;
  if (d==1) d=2;
  if (d==2) d=1;
  if (d==3) d=0;

}
void def_matrix(){
	for(i=0; i<20; i++){
	  for(j=0; j<20; j++){
		  visit_matrix[i][j] = 2;
		  temp_map[i][j] = 0;
    }
  }
}
//define starting position as well as the left and the right one

void def_start(){
	      temp_map[row][col] = 1;
        if (SensorValue(leftLight)< 30) temp_map[row][col - 1] = 1;
        else temp_map[row][col - 1] = 0;
        if (SensorValue(rightLight)< 40) temp_map[row][col - 1] = 1;
        else temp_map[row][col + 1] = 0;
        if (visit_matrix[row][col] != 7){
        	 visit_matrix[row][col] = 7;
        	 count++;                      //7 for visited node
        }                                //every time a new node is visited count is increasing
        if (visit_matrix[row][col-1] != 7){
        	 visit_matrix[row][col-1] = 7;
        	 count++;
        }
        if (visit_matrix[row][col+1] != 7){
        	 visit_matrix[row][col+1] = 7;
        	 count++;
        }
}

void def_position(){
	      switch (SensorValue[Colour]){
          case REDCOLOR:               //In case of red colour, we terminate
		        temp_map[row][col] = 9;    //9 for red point
		      break;
	      if (SensorValue[Colour]) temp_map[row][col] = 1;
	      else temp_map[row-1][col] = 0;
        if (SensorValue(leftLight)< 30) temp_map[row][col - 1] = 1;
        else temp_map[row][col - 1] = 0;
        if (SensorValue(rightLight)< 40) temp_map[row][col - 1] = 1;
        else temp_map[row][col + 1] = 0;
        if (visit_matrix[row][col] != 7){
        	 visit_matrix[row][col] = 7; //7 for visited
        	 count++;
        }
        if (visit_matrix[row][col-1] != 7){
        	 visit_matrix[row][col-1] = 7;
        	 count++;
        }
        if (visit_matrix[row][col+1] != 7){
        	 visit_matrix[row][col+1] = 7;
        	 count++;
        }

}





void dfs(int row,int col){

	while (count<=100 && temp_map[row][col] != 9){        //red point is a terminating condition
                                                        //Having updated all nodes (10x10=100=count) is a terminating condition as well
		if (temp_map[row][col] == 2 || temp_map[row][col] == 3 || temp_map[row][col] != 9 ) {
        visit_matrix[row][col] = 7;
        if (SensorValue[Colour] && temp_map[row][col] != 7){                      //for the movement priority we use up first then left, right, down.
        	forward();
        	m=1;
        }
        else if (SensorValue(leftLight)< 30 && temp_map[row-1][col] != 7){ //left
        	left_turn(d);
        	forward();
        	m=1;
        }
        else if (SensorValue(rightLight)< 40 && temp_map[row][col+1] != 7){ //right
        	right_turn(d);
        	forward();
        	m=1;
        }
        else if (!SensorValue[Colour] && SensorValue(rightLight)< 40 && temp_map[row][col-1] != 7){
        	full_turn(d);
					forward();
        	m=1;
        }
        while (!m){  //if no-movement choose up first then left, right, down
        	if (SensorValue[Colour]) forward();
        	else if (SensorValue(leftLight)< 30){
        	   left_turn(d);
        	   forward();
        	   m=1;
        	}
        	else if (SensorValue(rightLight)< 40){
        	   right_turn(d);
        	   forward();
        	   m=1;
        	}
        	else {
        		full_turn(d);
						forward();
        	  m=1;
        	}




        if (d==0) row--;
        if (d==1) col--;
        if (d==2) col++;
        if (d==3) row++;  //define position after movement
        def_position();

    }
	}
}
}

}

void create_final(){
	int found=0;
	int posi,posj,a;
	while (found!=0){
		  for(i=0; i<20; i++){
	       for(j=0; j<20; j++){
		        if (visit_matrix[i][j] == 7 && !found){
		        found=1;
		        posi=i;
		        posj=j;
		        }
         }
      }
   }
   for(i=posi; i<posi+10; i++){
	  for(j=posj; j<posj+10; j++){
		  final_map[i-posi][j-posj] = temp_map[i][j];
		  a = final_map[i-posi][j-posj];
		  AddToDatalog(a);
		 }
  }
  SaveNxtDatalog();
}


task main(){

def_start();
def_matrix();
dfs(10,10);
create_final();
}
