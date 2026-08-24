package nacholab.cv.pdf

@JsModule("pdfmake/build/pdfmake")
@JsNonModule
external val pdfMake: dynamic

@JsModule("pdfmake/build/vfs_fonts")
@JsNonModule
external val pdfFonts: dynamic

private var vfsInitialized = false

private fun ensureFontsInitialized() {
    if (vfsInitialized) return
    pdfMake.vfs = pdfFonts.pdfMake.vfs
    vfsInitialized = true
}

fun downloadPdf(docDefinition: dynamic, filename: String) {
    ensureFontsInitialized()
    pdfMake.createPdf(docDefinition).download(filename)
}

// TEMP: manual verification helper, removed once end-to-end testing is done.
fun pdfDataUrl(docDefinition: dynamic, onReady: (String) -> Unit) {
    ensureFontsInitialized()
    pdfMake.createPdf(docDefinition).getDataUrl { dataUrl: String -> onReady(dataUrl) }
}
