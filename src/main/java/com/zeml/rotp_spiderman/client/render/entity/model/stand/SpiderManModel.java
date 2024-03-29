package com.zeml.rotp_spiderman.client.render.entity.model.stand;

import com.zeml.rotp_spiderman.entity.stand.stands.SpiderManEntity;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.client.render.entity.model.stand.HumanoidStandModel;
import com.github.standobyte.jojo.client.render.entity.pose.ModelPose;
import com.github.standobyte.jojo.client.render.entity.pose.RotationAngle;
import com.github.standobyte.jojo.client.render.entity.pose.anim.PosedActionAnimation;
import com.github.standobyte.jojo.entity.stand.StandPose;

import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


public class SpiderManModel extends HumanoidStandModel<SpiderManEntity> {


	public SpiderManModel() {
		super();

		addHumanoidBaseBoxes(null);
		texWidth = 128;
		texHeight = 128;

	}

	@Override
	protected RotationAngle[][] initSummonPoseRotations() {
		return new RotationAngle[][] {
				new RotationAngle[] {
						RotationAngle.fromDegrees(head, 0, 0F, 0),
						RotationAngle.fromDegrees(body, 40.52f, 28.02f, 21.88f),
						RotationAngle.fromDegrees(upperPart, 0, 0, 0),
						RotationAngle.fromDegrees(leftArm, -61.68f, -13.27f, -7.05f),
						RotationAngle.fromDegrees(leftForeArm, 0f, 0f, 20f),
						RotationAngle.fromDegrees(rightArm, 26.8f, -3.78f, 86.17f),
						RotationAngle.fromDegrees(rightForeArm, -35f, 0f, 0f),
						RotationAngle.fromDegrees(leftLeg, -0.52f, 2.02f, -74.56f),
						RotationAngle.fromDegrees(leftLowerLeg, 0F, 0, 0F),
						RotationAngle.fromDegrees(rightLeg, -87.94f, 26.67f, 5.9f),
						RotationAngle.fromDegrees(rightLowerLeg, 67.5f, 0f, 0f)
				},
				new RotationAngle[] {
						RotationAngle.fromDegrees(head, 0, 0, 0),
						RotationAngle.fromDegrees(body, 42.5f, 0f, 0f),
						RotationAngle.fromDegrees(upperPart, 0, 0, 0),
						RotationAngle.fromDegrees(leftArm, 47.27f, 1.94f, -66.47f),
						RotationAngle.fromDegrees(leftForeArm, -57.5f, 0f, 0f),
						RotationAngle.fromDegrees(rightArm, 25f, 0f, 55f),
						RotationAngle.fromDegrees(rightForeArm, -67.5f, 0f, 0f),
						RotationAngle.fromDegrees(leftLeg, -108.11f, -33.64f, 10.27f),
						RotationAngle.fromDegrees(leftLowerLeg, 75.64f, -29.22f, 7.12f),
						RotationAngle.fromDegrees(rightLeg, -77.85f, 34.39f, 6.93f),
						RotationAngle.fromDegrees(rightLowerLeg, 73.26f, 41.27f, -11.22f)
				}
		};
	}

	@Override
	protected void initActionPoses() {
		actionAnim.put(StandPose.RANGED_ATTACK, new PosedActionAnimation.Builder<SpiderManEntity>()
				.addPose(StandEntityAction.Phase.BUTTON_HOLD, new ModelPose<>(new RotationAngle[] {
						RotationAngle.fromDegrees(body, 0f, -52.5f, 0f),
						RotationAngle.fromDegrees(leftArm, -10f, 0f, -70f),
						RotationAngle.fromDegrees(leftForeArm, -60f, 0f, 0f),
						RotationAngle.fromDegrees(rightArm, -22.17f, 16.12f, 70.77f),
						RotationAngle.fromDegrees(rightForeArm, -130.74f, 58.39f, -126.26f),
						RotationAngle.fromDegrees(leftLeg,-63.84f, -29.76f, -13.7f),
						RotationAngle.fromDegrees(leftLowerLeg,85f, 0f, 0f),
						RotationAngle.fromDegrees(rightLeg,-58.81f, 15.09f, 8.96f),
						RotationAngle.fromDegrees(rightLowerLeg,65f, 0f, 0f)
				}))
				.build(idlePose));

		super.initActionPoses();
	}

	@Override
	protected ModelPose<SpiderManEntity> initIdlePose() {
		return new ModelPose<>(new RotationAngle[] {
				RotationAngle.fromDegrees(body, -5F, 30F, 0.0F),
				RotationAngle.fromDegrees(upperPart, 0.0F, 0.0F, 0.0F),
				RotationAngle.fromDegrees(torso, 0.0F, 0.0F, 0.0F),
				RotationAngle.fromDegrees(leftArm, 12.5F, -30F, -15F),
				RotationAngle.fromDegrees(leftForeArm, -12.5F, 0.0F, 0.0F),
				RotationAngle.fromDegrees(rightArm, 10F, 30F, 15F),
				RotationAngle.fromDegrees(rightForeArm, -15F, 0.0F, 0.0F),
				RotationAngle.fromDegrees(leftLeg, 20F, 0.0F, 0.0F),
				RotationAngle.fromDegrees(leftLowerLeg, 0.0F, 0.0F, 0.0F),
				RotationAngle.fromDegrees(rightLeg, 0.0F, 0.0F, 0.0F),
				RotationAngle.fromDegrees(rightLowerLeg, 5F, 0.0F, 0.0F)
		});
	}

	@Override
	protected ModelPose<SpiderManEntity> initIdlePose2Loop() {
		return new ModelPose<>(new RotationAngle[] {
				RotationAngle.fromDegrees(leftArm, 7.5F, -30F, -15F),
				RotationAngle.fromDegrees(leftForeArm, -17.5F, 0.0F, 0.0F),
				RotationAngle.fromDegrees(rightArm, 12.5F, 30F, 15F),
				RotationAngle.fromDegrees(rightForeArm, -17.5F, 0.0F, 0.0F)
		});
	}
}