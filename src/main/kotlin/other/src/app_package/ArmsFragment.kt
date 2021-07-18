package other.src.app_package

import other.ArmsPluginTemplateProviderImpl
import other.commonAnnotation
import other.convertName

fun armsFragment(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl) = if (isKt) armsFragmentKt(provider) else armsFragmentJava(provider)

private fun armsFragmentKt(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.fragmentPackageName.value}
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import import com.tsy.commonsdk.base.fragment.BaseFragment
import com.jess.arms.di.component.AppComponent
import ${provider.componentPackageName.value}.Dagger${provider.pageName.value}Component
import ${provider.moudlePackageName.value}.${provider.pageName.value}Module
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract
import ${provider.presenterPackageName.value}.${provider.pageName.value}Presenter
import ${provider.appPackageName.value}.R
import ${provider.appPackageName.value}.databinding.${convertName(provider)}

${commonAnnotation(provider)}
class ${provider.pageName.value}Fragment : BaseFragment<${provider.pageName.value}Presenter,${convertName(provider)}>() , ${provider.pageName.value}Contract.View{
    companion object {
    fun newInstance():${provider.pageName.value}Fragment {
        val fragment = ${provider.pageName.value}Fragment()
        return fragment
    }
    }
    override fun setupFragmentComponent(appComponent:AppComponent) {
        Dagger${provider.pageName.value}Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .${provider.pageName.value[0].toLowerCase()}${provider.pageName.value.substring(1,provider.pageName.value.length)}Module(${provider.pageName.value}Module(this))
                .build()
                .inject(this)
    }
    
    /**
     * 初始化视图
     */
    override fun initBindingView(savedInstanceState: Bundle?) {

    }
    
    override fun initBindingData(savedInstanceState: Bundle?) {

    }
}
    
"""


fun armsFragmentJava(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.fragmentPackageName.value};
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import import com.tsy.commonsdk.base.fragment.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import ${provider.componentPackageName.value}.Dagger${provider.pageName.value}Component;
import ${provider.moudlePackageName.value}.${provider.pageName.value}Module;
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract;
import ${provider.presenterPackageName.value}.${provider.pageName.value}Presenter;
import ${provider.appPackageName.value}.R;
import ${provider.appPackageName.value}.databinding.${convertName(provider)}

${commonAnnotation(provider)}
class ${provider.pageName.value}Fragment extends BaseFragment<${provider.pageName.value}Presenter,${convertName(provider)}> implements ${provider.pageName.value}Contract.View{
    
    public static ${provider.pageName.value}Fragment newInstance() {
        ${provider.pageName.value}Fragment fragment = new ${provider.pageName.value}Fragment();
        return fragment;
    }
    
    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        Dagger${provider.pageName.value}Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }
    
    /**
     * 初始化视图
     */
    override fun initBindingView(savedInstanceState: Bundle?) {

    }
    
    override fun initBindingData(savedInstanceState: Bundle?) {

    }
}
    
"""