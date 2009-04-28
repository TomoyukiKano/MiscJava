package sample.customizer;

import java.lang.annotation.Annotation;

import org.seasar.framework.aop.Pointcut;
import org.seasar.framework.container.customizer.AspectCustomizer;
import org.seasar.framework.util.StringUtil;

import sample.aop.AnnotatedPointcut;

/**
 * {@link AspectCustomizer}を拡張したコンポーネントカスタマイザです。
 * <p>
 * ポイントカットとしてメソッド名に加えてメソッドに指定されたアノテーションを使用できるようにします。
 * </p>
 *
 * @author tomo
 */
public class AnnotatedAspectCustomizer extends AspectCustomizer {

    private Class<? extends Annotation> annotation;

    // AspectCustomizer.pointcut がアクセスできないので冗長なコードです
    private String pointcut; 

    @Override
	public void setPointcut(final String pointcut) {
        this.pointcut = pointcut;
    }

    /**
     * コンポーネント定義に登録するアスペクト定義のポイントカットのアノテーションを設定します。
     *
     * @param annotation
     *            ポイントカット
     */
    public void setAnnotation(
			      final Class<? extends Annotation> annotation) {
        this.annotation = annotation;
    }

    @Override
	protected Pointcut createPointcut() {
        if (!StringUtil.isEmpty(pointcut)) {
            String[] methodNames = StringUtil.split(pointcut, ", \n");
            return new AnnotatedPointcut(annotation, methodNames);
        }
        if (targetInterface != null) {
            return new AnnotatedPointcut(annotation, targetInterface);
        }
        return null;
    }
}
