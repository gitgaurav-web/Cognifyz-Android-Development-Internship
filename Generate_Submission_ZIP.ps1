# Script to create the final submission ZIP file for Cognifyz Internship
$zipName = "e:\intership\Cognifyz_Android_Internship_Gaurav_Kumar.zip"

if (Test-Path $zipName) {
    Remove-Item -Force $zipName
}

$itemsToCompress = @(
    "e:\intership\Level_1_Beginner",
    "e:\intership\Level_2_Intermediate",
    "e:\intership\Level_3_Advanced",
    "e:\intership\Level_4_Expert",
    "e:\intership\Cognifyz_Android_Internship_App",
    "e:\intership\PROJECT_DOCUMENTATION.md",
    "e:\intership\LINKEDIN_POST_TEMPLATE.md"
)

Compress-Archive -Path $itemsToCompress -DestinationPath $zipName -CompressionLevel Optimal
Write-Host "? Successfully created submission ZIP archive at: $zipName" -ForegroundColor Green
