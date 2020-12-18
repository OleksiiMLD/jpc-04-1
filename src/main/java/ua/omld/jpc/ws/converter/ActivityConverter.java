package ua.omld.jpc.ws.converter;

import jpc.omld.ua.Activity;
import jpc.omld.ua.ActivityMeasurement;
import jpc.omld.ua.ObjectFactory;
import org.springframework.stereotype.Component;

/**
 * @author Oleksii Kostetskyi
 */
@Component("ws.ActivityConverter")
public class ActivityConverter extends Converter<Activity, ua.omld.jpc.entity.Activity> {

	public ActivityConverter(ObjectFactory objectFactory) {
		super(ActivityConverter::convertToEntity, ActivityConverter::convertToJAXB);
		ActivityConverter.objectFactory = objectFactory;
	}

	private static Activity convertToJAXB(ua.omld.jpc.entity.Activity activity) {
		if (activity == null) {
			return null;
		}
		Activity jaxbActivity = objectFactory.createActivity();
		jaxbActivity.setId(objectFactory.createActivityId(activity.getId()));
		jaxbActivity.setWorkName(objectFactory.createActivityWorkName(activity.getWorkName()));
		jaxbActivity.setAmount(objectFactory.createActivityAmount(activity.getAmount()));
		jaxbActivity.setPrice(objectFactory.createActivityPrice(activity.getPrice()));
		jaxbActivity.setMeasurement(objectFactory.createActivityMeasurement(
				ActivityMeasurement.fromValue(activity.getMeasurement().name())));
		return jaxbActivity;
	}

	private static ua.omld.jpc.entity.Activity convertToEntity(Activity jaxbActivity) {
		if (jaxbActivity == null) {
			return null;
		}
		ua.omld.jpc.entity.Activity activity = new ua.omld.jpc.entity.Activity();
		activity.setId(jaxbActivity.getId().getValue());
		activity.setWorkName(jaxbActivity.getWorkName().getValue());
		activity.setAmount(jaxbActivity.getAmount().getValue());
		activity.setPrice(jaxbActivity.getPrice().getValue());
		return activity;
	}
}