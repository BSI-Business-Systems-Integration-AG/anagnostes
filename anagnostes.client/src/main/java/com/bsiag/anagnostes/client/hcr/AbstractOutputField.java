package com.bsiag.anagnostes.client.hcr;

import java.util.UUID;

import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.dto.FormData.DefaultSubtypeSdkCommand;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.imagefield.AbstractImageField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.resource.BinaryResource;

import com.bsiag.anagnostes.shared.hcr.AbstractOutputFieldData;
import org.eclipse.scout.rt.platform.classid.ClassId;

@ClassId("bb928ae0-0fc9-46af-8fab-c70b27d92d5e")
@FormData(value = AbstractOutputFieldData.class, sdkCommand = FormData.SdkCommand.CREATE, defaultSubtypeSdkCommand = DefaultSubtypeSdkCommand.CREATE)
public class AbstractOutputField extends AbstractGroupBox {

	private BinaryResource m_image;

	@Override
	protected boolean getConfiguredBorderVisible() {
		return false;
	}

	@Override
	protected int getConfiguredGridColumnCount() {
		return 1;
	}

	@Override
	protected int getConfiguredGridW() {
		return 1;
	}

	@FormData
	public BinaryResource getImage() {
		return m_image;
	}

	@FormData
	public void setImage(BinaryResource image) {
		m_image = image;
		getNumberImageField().setImage(image.getContent());
	}

	public NumberImageField getNumberImageField() {
		return getFieldByClass(NumberImageField.class);
	}

	public OutputValueField getOutputValueField() {
		return getFieldByClass(OutputValueField.class);
	}

	public ConfidenceField getConfidenceField() {
		return getFieldByClass(ConfidenceField.class);
	}

	@Order(1000)
	@ClassId("998fa1c4-42a9-425b-8998-a60f5c91cd4a")
	public class NumberImageField extends AbstractImageField {

		@Override
		protected int getConfiguredHeightInPixel() {
			return 100;
		}

		@Override
		protected boolean getConfiguredLabelVisible() {
			return false;
		}
		
		@Override
		public String classId() {
			return UUID.randomUUID().toString();
		}
	}

	@Order(2000)
	@ClassId("0e262d0d-b8a1-440b-a8b8-3150c804dee8")
	public class OutputValueField extends AbstractStringField {

		@Override
		protected boolean getConfiguredLabelVisible() {
			return false;
		}

		@Override
		protected int getConfiguredWidthInPixel() {
			return 100;
		}
		
		@Override
		public String classId() {
			return UUID.randomUUID().toString();
		}
	}

	@Order(3000)
	@ClassId("c0d7d805-3cf1-4997-be57-32c7b92dbb52")
	public class ConfidenceField extends AbstractStringField {

		@Override
		protected boolean getConfiguredLabelVisible() {
			return false;
		}

		@Override
		protected int getConfiguredWidthInPixel() {
			return 100;
		}

		@Override
		protected String getConfiguredFormat() {
			return "0.000";
		}

		@Override
		public String classId() {
			return UUID.randomUUID().toString();
		}
		
		public void updateColor() {
			try {
				double value = Double.parseDouble(getValue());
				double rV = value < 0.75 ? 1 : 1.0 - ((value - 0.75) * 4.0);
				double gV = value < 0.5 ? 0 : value > 0.75 ? 1 : (value - 0.5) * 4.0;

				int r = (int) (rV * 255);
				int g = (int) (gV * 255);
				int b = 0;

				setBackgroundColor(String.format("%02X%02X%02X", r, g, b));
			} catch (NumberFormatException | NullPointerException e) {
				// nop: do not format
			}
		}
	}
}
