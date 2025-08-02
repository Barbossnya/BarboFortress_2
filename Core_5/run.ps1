Write-Host "Compiling Java files..." -ForegroundColor Green
javac src/*.java

Write-Host "`nRunning the application..." -ForegroundColor Green
java -cp src Main

Write-Host "`nPress any key to continue..." -ForegroundColor Yellow
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown") 