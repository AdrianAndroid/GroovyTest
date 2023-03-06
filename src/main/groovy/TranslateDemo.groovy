import com.google.cloud.translate.Translate
import com.google.cloud.translate.TranslateOptions
import com.google.cloud.translate.Translation

// https://cloud.google.com/translate/docs/languages?hl=zh-cn
class TranslateDemo {
    static void main(String[] args) {
        // Instantiates a client
        Translate translate = TranslateOptions.newBuilder()
            .setApiKey("")
            .build()
            .getService()
        // The text to translate
        String text = "Hello, world!"
        Translation translation = translate.translate(text,
                Translate.TranslateOption.sourceLanguage("en"),
                Translate.TranslateOption.targetLanguage("ru")
        )
        println("text: "+text)
        println("Translation: ${translation.getTranslatedText()}")
    }
}
