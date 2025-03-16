背景：
1. 品管於NF AC300A試驗台上，測試斷路器電流是否符合105%及130%之規定，此機台上只有平頭按鈕開關及計時器顯示秒數，且秒數無傳輸功能。
2. 105%及130%試驗合格條件：當電流小於63A，105%試驗於1小時內不跳脫，130%於1小時內需跳脫；當電流大於63A，105%試驗於2小時內不跳脫，130%於2小時內需跳脫。
3. 上述試驗因耗時，故試驗人員需要定時到試驗站確認試驗秒數及斷路器狀態是否跳脫。

改善後：
1.	將NF AC300A試驗台上的a接點接至三菱PLC，並增加網路擴充模組與電腦進行通訊。
2.	撰寫C#程式語言建立操作者介面，使用者選擇產品規格，並依需求是否將試驗結果存到資料庫或發送Line訊息，測試完成後會自動判斷是否符合規定。
3.	撰寫Java程式語言建置網站，可即時存取資料庫，取得產品規格、試驗秒數及試驗狀態，傳至前端網頁畫面及發送Line訊息。
4.	改善後，使用者可於電腦網頁上，隨時查看當前試驗秒數、試驗狀態及歷史資料。
5.	改善後，使用者可於手機Line中，隨時收到105%及130%試驗完成通知及試驗秒數。

效益：
導入測試機台自動化通報系統，每月節省約4000元人力成本。

