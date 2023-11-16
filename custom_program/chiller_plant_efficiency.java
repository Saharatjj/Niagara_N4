/* Add a Slot in Niagara's program to state the inputs and outputs
Inputs:
- (inA) coolingLoad: numeric
- (inB) plantPower: numeric
- (inC) chillerPower: numeric
- (inD) priPumpPower: numeric
- (inE) secPumpPower: numeric
- (inF) condPumpPower: numeric
- (inG) coolingTowerPower: numeric
Outputs:
- (outA) plantEfficiency: numeric
- (outB) chillerEfficiency: numeric
- (outC) priPumpEfficiency: numeric
- (outD) secPumpEfficiency: numeric
- (outE) condPumpEfficiency: numeric
- (outF) coolingTowerEfficiency: numeric
*/

public void onStart() throws Exception
{
  // start up code here
}

public void onExecute() throws Exception
{
  // inputs
  BStatusNumeric coolingLoad = getInA();
  BStatusNumeric plantPower = getInB();
  BStatusNumeric chillerPower = getInC();
  BStatusNumeric priPumpPower = getInD();
  BStatusNumeric secPumpPower = getInE();
  BStatusNumeric condPumpPower = getInF();
  BStatusNumeric coolingTowerPower = getInG();
  
  // outputs
  BStatusNumeric plantEfficiency = getOutA();
  BStatusNumeric chillerEfficiency = getOutB();
  BStatusNumeric priPumpEfficiency = getOutC();
  BStatusNumeric secPumpEfficiency = getOutD();
  BStatusNumeric condPumpEfficiency = getOutE();
  BStatusNumeric coolingTowerEfficiency = getOutF();
  
  // process
  plantEfficiency.setValue(plantPower.getValue()/coolingLoad.getValue());
  chillerEfficiency.setValue(chillerPower.getValue()/coolingLoad.getValue());
  priPumpEfficiency.setValue(priPumpPower.getValue()/coolingLoad.getValue());
  secPumpEfficiency.setValue(secPumpPower.getValue()/coolingLoad.getValue());
  condPumpEfficiency.setValue(condPumpPower.getValue()/coolingLoad.getValue());
  coolingTowerEfficiency.setValue(coolingTowerPower.getValue()/coolingLoad.getValue());
}

public void onStop() throws Exception
{
  // shutdown code here
}

