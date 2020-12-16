package ua.omld.jpc.ws.converter;

import jpc.omld.ua.ObjectFactory;
import jpc.omld.ua.Report;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import ua.omld.jpc.exception.ConvertException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;

/**
 * @author Oleksii Kostetskyi
 */
@Component
public class ReportConverter extends Converter<Report, ua.omld.jpc.entity.Report> {

	private static final Logger LOGGER = LogManager.getLogger();

	public ReportConverter(ObjectFactory objectFactory) {
		super(ReportConverter::convertToEntity, ReportConverter::convertToJAXB);
		ReportConverter.objectFactory = objectFactory;
	}

	private static Report convertToJAXB(ua.omld.jpc.entity.Report report) {
		if (report == null) {
			return null;
		}
		XMLGregorianCalendar xmlOrderDate;
		try {
			xmlOrderDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(report.getOrderDate().toString());
		} catch (DatatypeConfigurationException e) {
			LOGGER.error("Cannot convert JAXB to entity: " + e.getMessage());
			throw new ConvertException("Error convert report date.", e);
		}
		Report jaxbReport = objectFactory.createReport();
		jaxbReport.setId(objectFactory.createReportId(report.getId()));
		jaxbReport.setName(objectFactory.createReportName(report.getName()));
		jaxbReport.setOrderDate(objectFactory.createReportOrderDate(xmlOrderDate));
		jaxbReport.setName(objectFactory.createReportName(report.getName()));
		return jaxbReport;
	}

	private static ua.omld.jpc.entity.Report convertToEntity(Report jaxbReport) {
		if (jaxbReport == null) {
			return null;
		}
		XMLGregorianCalendar xmlGregorianCalendar = jaxbReport.getOrderDate().getValue();
		LocalDate localDate = LocalDate.of(xmlGregorianCalendar.getYear(), xmlGregorianCalendar.getMonth(),
				xmlGregorianCalendar.getDay());
		ua.omld.jpc.entity.Report report = new ua.omld.jpc.entity.Report();
		report.setId(jaxbReport.getId().getValue());
		report.setName(jaxbReport.getName().getValue());
		report.setPrice(jaxbReport.getPrice().getValue());
		report.setOrderDate(localDate);
		return report;
	}
}
