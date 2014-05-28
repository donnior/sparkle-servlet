package me.donnior.sparkle.servlet;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.lang.annotation.Annotation;
import java.util.List;

import me.donnior.sparkle.annotation.Json;
import me.donnior.sparkle.annotation.Param;
import me.donnior.sparkle.core.ActionMethodDefinition;
import me.donnior.sparkle.core.ActionMethodParamDefinition;
import me.donnior.sparkle.core.resolver.ActionMethodDefinitionFinder;
import me.donnior.sparkle.core.resolver.DefaulActionParamDefinition;
import me.donnior.sparkle.core.resolver.SimpleArgumentResolver;
import me.donnior.sparkle.servlet.ServletWebRequest;
import me.donnior.sparkle.servlet.resolver.ParamInstanceArgumentResolver;
import me.donnior.sparkle.servlet.resolver.ServletEnvironmentArgumentResolverManager;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

public class ServletEnvironmentArgumentResolverManagerTest {
    
    private ServletEnvironmentArgumentResolverManager manager;
    
    @Before
    public void setup(){
        manager = new ServletEnvironmentArgumentResolverManager();
    }
    
    @Test
    public void testDefaultConstructor(){
        assertEquals(6, manager.registeredResolvers().size());
        assertEquals(ParamInstanceArgumentResolver.class, manager.registeredResolvers().get(3).getClass());
        assertEquals(SimpleArgumentResolver.class, manager.registeredResolvers().get(4).getClass());
    }
    
    @Test (expected=RuntimeException.class)
    public void testCannotFindProperResovler(){
        
        List<Annotation> annotations = Lists.newArrayList();
        annotations.add(new Annotation() {
            
            @Override
            public Class<? extends Annotation> annotationType() {
                return Json.class;
            }
        });
        
        ActionMethodParamDefinition actionParamDefinition = 
                new DefaulActionParamDefinition(String.class, annotations);
        
        manager.resolve(actionParamDefinition, null);
        fail();
    }

    @Test
    public void testResovleSucced(){
        
        ActionMethodDefinition actionParamDefinition = 
                new ActionMethodDefinitionFinder().find(ContrllerForDefaultParamResolversManagerTest.class, "index"); 
        assertEquals(1, actionParamDefinition.paramDefinitions().size());
               
                
        Object obj = manager.resolve(actionParamDefinition.paramDefinitions().get(0), 
                new ServletWebRequest(new HttpServletRequestAdapter(),null));
        assertNull(obj);
    }
    
    
}

class ContrllerForDefaultParamResolversManagerTest{
    
    @Json
    public String index(@Param("page") Integer page){
        return null;
    }
    
    public String show(){
        return null;
    }
    
    public String show(int id){
        return null;
    }
}