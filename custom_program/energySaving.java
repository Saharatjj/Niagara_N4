/*
Import Module
baja - javax.baja.naming
baja - javax.baja.collection
history - javax.baja.history
history - javax.baja.history.db

Inputs:
- (inA) scheduleAction: boolean
- (inB) baselineEfficiency: numeric
Outputs:
- (outA) energySaving: numeric
*/

public void onStart() throws Exception
{
  // start up code here
}

public void onExecute() throws Exception
{
  // Setup Inputs/Outputs
  BStatusBoolean scheduleAction = getInA();
  BStatusNumeric baselineEfficiency = getInB();
  BStatusNumeric energySaving = getOutA();
  
  // Setup Time
  // BAbsTime.make(year, month, day, hour, minute, second, millis)
  BAbsTime now = BAbsTime.now();
  BAbsTime startTime = BAbsTime.make(now.getYear(), BMonth.january, 1, 0, 0, 0, 0);
  
  // Setup Database
  String historyDevice = "CPMS";
  String coolingLoadName = "CoolingLoad";
  String powerConsumptionName = "PowerConsumption";
  
  BHistoryService service = (BHistoryService)Sys.getService(BHistoryService.TYPE);
  BHistoryDatabase db = service.getDatabase();
  
  double coolingLoadAverage = getAverageData(db, historyDevice, coolingLoadName, startTime, now);
  double powerAverage = getAverageData(db, historyDevice, powerConsumptionName, startTime, now);
  double hourDiff = getHourDifferent(startTime, now);
  
  double coolingLoadhour = coolingLoadAverage * hourDiff;
  double powerActual = powerAverage * hourDiff;
  
  double powerBaseline = coolingLoadhour * baselineEfficiency.getValue();
  
  energySaving.setValue(calculateSaving(powerBaseline, powerActual));
}

public double getAverageData(BHistoryDatabase db, String historyDevice, String historyName, BAbsTime startTime, BAbsTime endTime) throws Exception {
  
  double sum = 0;
  int count = 0;
  BHistoryId historyId = BHistoryId.make("/" + SlotPath.escape(historyDevice) + "/" + SlotPath.escape(historyName));
  
  try (HistorySpaceConnection conn = db.getConnection(null)) {
    boolean exists = conn.exists(historyId);
    
    if (exists) {
      BIHistory history = conn.getHistory(historyId);
      BITable<BHistoryRecord> collection = conn.timeQuery(history, startTime, endTime);
      
      if (collection != null) {
        try (Cursor<BHistoryRecord> cursor = collection.cursor()) {
          while (cursor.next()) {
            BHistoryRecord rec = cursor.get();
            if (rec instanceof BNumericTrendRecord) {
              BNumericTrendRecord numRec = (BNumericTrendRecord) rec;
              sum += numRec.getValue();
              count++;
            }
          }
        }
        return sum / count;
      }
      else {
       return 0;
      }
    }
    else {
      return 0;
    }
  }
}

public double getHourDifferent(BAbsTime startTime, BAbsTime endTime) throws Exception {
  long startTimeMillis = startTime.getMillis();
  long endTimeMillis = endTime.getMillis();
  
  return (double) ((endTimeMillis - startTimeMillis)/ (1000 * 60 * 60));
}

public double calculateSaving(double baseline, double actual) throws Exception {
  return baseline - actual;
}

public void onStop() throws Exception
{
  // shutdown code here
}
