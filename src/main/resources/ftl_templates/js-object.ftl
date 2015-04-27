function ${javaClassName}()  {
	var self = this;
	
	self.id = '';
<#list attributeDefinitions as attr>
	self.${attr.name} = null;
</#list>
}