/* Add a Slot in Niagara's program to state the inputs and outputs
Inputs:
- (inA) statusCH1: boolean
- (inB) statusCH2: boolean
- (inC) statusCH3: boolean
- (inD) statusCH4: boolean
- (inE) maxRTCH1: numeric
- (inF) maxRTCH2: numeric
- (inG) maxRTCH3: numeric
- (inH) maxRTCH4: numeric
- (inI) coolingLoad: numeric
Outputs:
- (outA) partLoad: numeric
*/

public void onStart() throws Exception
{
  // start up code here
}

public void onExecute() throws Exception
{
  // inputs
  BStatusBoolean statusCH1 = getInA();
  BStatusBoolean statusCH2 = getInB();
  BStatusBoolean statusCH3 = getInC();
  BStatusBoolean statusCH4 = getInD();
  BStatusNumeric maxRTCH1 = getInE();
  BStatusNumeric maxRTCH2 = getInF();
  BStatusNumeric maxRTCH3 = getInG();
  BStatusNumeric maxRTCH4 = getInH();
  BStatusNumeric coolingLoad = getInI();

  // outputs
  BStatusNumeric partLoad = getOutA();
  
  double maxTotalRT = 0;
  
  if (statusCH1.getValue()) {
    maxTotalRT += maxRTCH1.getValue();
  }
  if (statusCH2.getValue()) {
    maxTotalRT += maxRTCH2.getValue();
  }
  if (statusCH3.getValue()) {
    maxTotalRT += maxRTCH3.getValue();
  }
  if (statusCH4.getValue()) {
    maxTotalRT += maxRTCH4.getValue();
  }

  if (maxTotalRT > 0) {
    partLoad.setValue((coolingLoad.getValue()/maxTotalRT)*100);
  }
  else {
    partLoad.setValue(0.0);
  }
}

public void onStop() throws Exception
{
  // shutdown code here
}