# ncu2015-nobody

簡易教學XD  

首先開啟你的git工具 (git power shell, git bash等等)  
切換到你想要複製的資料夾  

複製整份repository  
git clone git@github.com:NCU-2015-nobody/ncu2015-nobody.git  

這樣就複製完成了, 接著開啟eclpise將此專案匯入  
但是我們希望不要上傳 /bin 和 /.setting資料夾  
所以repository裡面的 .gitignore 就發揮他的功用拉~  
.gitignore裡面已經設定好了, 會自動忽略不必要的檔案  

不過也因為這樣子, clone的檔案中並沒有Eclipse需要的專案設定檔  
http://stackoverflow.com/questions/2636201/how-to-create-a-project-from-existing-source-in-eclipse-and-then-find-it  
參考這篇文章  
你只要正常使用原本的開啟新專案的流程 
把專案名稱和clone資料夾的名稱設定一模一樣  
Eclipse會保留裡面其他檔案, 在Eclipse中也可以看到一個新專案了~  

請大家注意一下  
由於我們的repository只有一個專案檔  
如果想要新增程式碼  
請新增自己的branch後再新增  
不要直接把新的程式碼直接加進master中  
這樣以後合併可能會有很多衝突發生  
