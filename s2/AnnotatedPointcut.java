package sample.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.seasar.framework.aop.impl.PointcutImpl;

public class AnnotatedPointcut extends PointcutImpl {

    private static final long serialVersionUID = 4297317225076325185L;

    private Class<? extends Annotation> annotation;

    public AnnotatedPointcut(Class<? extends Annotation> annotation,
			     String[] methodNaames) {
        super(methodNaames);
        if (!annotation.isAnnotation()) {
            throw new IllegalArgumentException("is not annotation");
        }
        this.annotation = annotation;
    }

    public AnnotatedPointcut(Class<? extends Annotation> annotation,
			     Class<?> targetClass) {
        super(targetClass);
        if (!annotation.isAnnotation()) {
            throw new IllegalArgumentException("is not annotation");
        }
        this.annotation = annotation;
    }

    @Override
	public boolean isApplied(Method method) {
        return super.isApplied(method) &&
            method.isAnnotationPresent(annotation);
    }
}
