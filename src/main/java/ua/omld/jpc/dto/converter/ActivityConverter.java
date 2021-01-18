package ua.omld.jpc.dto.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import ua.omld.jpc.dto.ActivityDto;
import ua.omld.jpc.entity.Activity;
import ua.omld.jpc.entity.ActivityMeasurement;

/**
 * @author Oleksii Kostetskyi
 */
@Component
public class ActivityConverter extends Converter<ActivityDto, Activity> {

	public ActivityConverter() {
		super(ActivityConverter::convertToEntity, ActivityConverter::convertToDto);
	}

	private static ActivityDto convertToDto(Activity activity) {
		if (activity == null) {
			return null;
		}
		ActivityDto activityDto = new ActivityDto();
		BeanUtils.copyProperties(activity, activityDto);
		activityDto.setMeasurement(activity.getMeasurement() == null ? null : activity.getMeasurement().name());
		return activityDto;
	}

	private static Activity convertToEntity(ActivityDto activityDto) {
		if (activityDto == null) {
			return null;
		}
		Activity activity = new Activity();
		BeanUtils.copyProperties(activityDto, activity);
		activity.setMeasurement(activityDto.getMeasurement() == null ? null :
				ActivityMeasurement.valueOf(activityDto.getMeasurement().toUpperCase()));
		return activity;
	}
}