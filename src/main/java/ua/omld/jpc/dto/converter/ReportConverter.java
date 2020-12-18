package ua.omld.jpc.dto.converter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import ua.omld.jpc.dto.ReportDto;
import ua.omld.jpc.entity.Report;

/**
 * @author Oleksii Kostetskyi
 */
@Component
public class ReportConverter extends Converter<ReportDto, Report> {

	private static final Logger LOGGER = LogManager.getLogger();

	public ReportConverter() {
		super(ReportConverter::convertToEntity, ReportConverter::convertToDto);
	}

	private static ReportDto convertToDto(Report report) {
		if (report == null) {
			return null;
		}
		ReportDto reportDto = new ReportDto();
		BeanUtils.copyProperties(report, reportDto, "buildings");
//		reportDto.setOrderDate(objectFactory.createReportOrderDate(xmlOrderDate));
		return reportDto;
	}

	private static Report convertToEntity(ReportDto reportDto) {
		if (reportDto == null) {
			return null;
		}
		Report report = new Report();
		BeanUtils.copyProperties(reportDto, report);
//		report.setId(reportDto.getId());
//		report.setName(reportDto.getName());
//		report.setPrice(reportDto.getPrice());
//		LocalDate localDate = LocalDate.parse(reportDto.getOrderDate());
//		report.setOrderDate(localDate);
		return report;
	}
}
