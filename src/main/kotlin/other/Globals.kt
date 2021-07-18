package other

import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

fun commonAnnotation(provider: ArmsPluginTemplateProviderImpl) = """
/**
 * 
 * @author like 
 * @date ${SimpleDateFormat("yyyy/MM/dd HH:mm").format(Date(System.currentTimeMillis()))}
 */
""".trimIndent()

val armsAnnotation = """
/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on ${SimpleDateFormat("yyyy/MM/dd HH:mm").format(Date(System.currentTimeMillis()))}
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
""".trimIndent()

fun convertName(provider: ArmsPluginTemplateProviderImpl) = """
    ${convertNameString(provider.activityLayoutName.value)}Binding
""".trimIndent()

fun convertNameString(name: String): String {
    var result: String = ""
    if (name != null) {
        if (!Pattern.compile(".*[_]+.*").matcher(name).matches()) {//是否是下划线命名
            if (!Pattern.compile(".*[A-Z]+.*").matcher(name).matches()) {//是否包含大写字母
                return name
            }
            result = name.replace("([a-z])([A-Z])", "$1" + "_" + "$2").toUpperCase()
        } else {
            name.split("_").forEach { s ->
                result = result + s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase()
            }
        }
    } else {
        return ""
    }
    return result
}