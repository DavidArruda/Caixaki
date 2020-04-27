package br.com.project.report.util;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import br.com.project.util.all.BeanViewAbstract;

@Component
public class BeanReportView extends BeanViewAbstract {

	private static final long serialVersionUID = 1L;

	@Resource
	private ReportUtil reportUtil;
}
